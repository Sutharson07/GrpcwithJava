// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: schema.proto

// Protobuf Java Version: 3.25.6
package com.example.grpc;

public final class EmployeeProto {
  private EmployeeProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EmployeeRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EmployeeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_EmployeeResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_EmployeeResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\014schema.proto\"\035\n\017EmployeeRequest\022\n\n\002id\030" +
      "\001 \001(\005\"@\n\020EmployeeResponse\022\n\n\002id\030\001 \001(\005\022\014\n" +
      "\004name\030\002 \001(\t\022\022\n\ndepartment\030\003 \001(\t2E\n\017Emplo" +
      "yeeService\0222\n\013GetEmployee\022\020.EmployeeRequ" +
      "est\032\021.EmployeeResponseB#\n\020com.example.gr" +
      "pcB\rEmployeeProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_EmployeeRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_EmployeeRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EmployeeRequest_descriptor,
        new java.lang.String[] { "Id", });
    internal_static_EmployeeResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_EmployeeResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_EmployeeResponse_descriptor,
        new java.lang.String[] { "Id", "Name", "Department", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
