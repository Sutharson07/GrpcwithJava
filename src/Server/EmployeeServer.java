package Server;

import com.example.grpc.EmployeeRequest;
import com.example.grpc.EmployeeResponse;
import com.example.grpc.EmployeeServiceGrpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class EmployeeServer {
    // Server configuration constants
    private static final int SERVER_PORT = 50051;
    private static final String SERVER_HOST = "0.0.0.0";
    
    // gRPC server instance
    private Server server;

    public static void main(String[] args) throws Exception {
        final EmployeeServer employeeServer = new EmployeeServer();
        employeeServer.start();
        employeeServer.blockUntilShutdown();
    }

    private void start() throws Exception {
        System.setProperty("io.netty.noPreferDirect", "false");
        
        // Build and start the server
        server = ServerBuilder.forPort(SERVER_PORT)
                .addService(new EmployeeServiceImpl())
                .build()
                .start();
        
        System.out.println("Server started, listening on port " + SERVER_PORT);
        
        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            EmployeeServer.this.stop();
            System.err.println("Server successfully shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    // Service implementation
    static class EmployeeServiceImpl extends EmployeeServiceGrpc.EmployeeServiceImplBase {
        @Override
        public void getEmployee(EmployeeRequest request, StreamObserver<EmployeeResponse> responseObserver) {
            System.out.println("Received request for employee ID: " + request.getId());
            
            // Simulate database lookup or business logic
            String employeeName = "Employee " + request.getId();
            String department = determineDepartment(request.getId());
            
            // Build response
            EmployeeResponse response = EmployeeResponse.newBuilder()
                    .setId(request.getId())
                    .setName(employeeName)
                    .setDepartment(department)
                    .build();

            // Send response
            responseObserver.onNext(response);
            responseObserver.onCompleted();
            
            System.out.println("Response sent for employee ID: " + request.getId());
        }
        
        private String determineDepartment(int employeeId) {
            // Simple department assignment logic
            return switch (employeeId % 3) {
                case 0 -> "Engineering";
                case 1 -> "Marketing";
                case 2 -> "Human Resources";
                default -> "General";
            };
        }
    }
}