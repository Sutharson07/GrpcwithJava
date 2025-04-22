package Server;

import com.example.grpc.EmployeeRequest;
import com.example.grpc.EmployeeResponse;
import com.example.grpc.EmployeeServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class EmployeeClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 50051;

    public static void main(String[] args) {
        System.setProperty("io.netty.noPreferDirect", "false");
        
        // Create channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext() // No SSL
                .build();

        try {
            // Create async stub
            EmployeeServiceGrpc.EmployeeServiceStub stub = EmployeeServiceGrpc.newStub(channel);

            // Build request
            EmployeeRequest request = EmployeeRequest.newBuilder()
                    .setId(1) // Example employee ID
                    .build();

            System.out.println("Sending request for employee ID: " + request.getId());
            
            // Make the call
            stub.getEmployee(request, new StreamObserver<EmployeeResponse>() {
                @Override
                public void onNext(EmployeeResponse response) {
                    System.out.println("\nReceived Employee Details:");
                    System.out.println("ID: " + response.getId());
                    System.out.println("Name: " + response.getName());
                    System.out.println("Department: " + response.getDepartment());
                }

                @Override
                public void onError(Throwable t) {
                    System.err.println("Error in gRPC call:");
                    t.printStackTrace();
                    channel.shutdownNow();
                }

                @Override
                public void onCompleted() {
                    System.out.println("Call completed successfully");
                    channel.shutdown();
                }
            });
            
            // Keep the client alive to receive response
            Thread.sleep(3000); // Adjust as needed
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // Ensure channel is shutdown if not already
            if (!channel.isShutdown()) {
                channel.shutdownNow();
            }
        }
    }
}