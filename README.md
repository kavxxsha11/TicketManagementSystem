Real-Time Event Ticketing System - CLI
Overview
This Command-Line Interface (CLI) application is part of a Real-Time Event Ticketing System. It provides an interactive way to configure, start, stop, and monitor the ticketing system's backend operations.
Features

System Configuration
Start/Stop Ticketing Operations
Check System Status
Interactive Menu-Driven Interface

Prerequisites

Java Development Kit (JDK) 11 or higher
Maven or Gradle (for dependency management)

Setup and Installation

Clone the repository
Navigate to the CLI project directory
Ensure all dependencies are installed
Compile the project

Available Commands

configure: Set system parameters (total tickets, release rates, etc.)
start: Begin ticketing operations
stop: Halt ticketing operations
status: Check current system status
exit: Close the CLI application

Configuration Parameters

Total Tickets
Ticket Release Rate
Customer Retrieval Rate
Maximum Ticket Capacity

Dependencies

Java Networking (HttpURLConnection)
JSON Configuration Management

Notes

Ensure the backend Spring Boot application is running
The CLI communicates with the backend via HTTP endpoints
Configuration is persistent between sessions

Troubleshooting

Verify backend service is running on localhost:8080
Check network connectivity
Ensure proper Java version is installed


Real-Time Event Ticketing System - Backend
Overview
A Spring Boot-based backend for a Real-Time Event Ticketing System, implementing a multi-threaded ticket management platform with configurable system parameters.
Technical Stack

Java 11+
Spring Boot
Multi-threading
Jackson for JSON Configuration
Lombok for simplified model classes

System Architecture

Vendor Threads: Generate tickets
Customer Threads: Purchase tickets
Ticket Pool: Centralized ticket management
Configuration Management: Dynamic system settings

Key Components

Configure: System configuration management
RequestHandler: REST API endpoints
TicketService: Core business logic
TicketPool: Synchronized ticket management
VendorThread & CustomerThread: Concurrent ticket operations

Prerequisites

Java Development Kit (JDK) 11+
Maven
Spring Boot

Configuration
System can be configured through:

CLI interface
Configuration JSON file
Runtime configuration endpoints

Configuration Parameters

Total Tickets
Ticket Release Rate
Customer Retrieval Rate
Maximum Ticket Capacity

API Endpoints

POST /api/system/configure: Set system parameters
POST /api/system/start: Start ticket system
POST /api/system/stop: Stop ticket system
GET /api/system/status: Check system status
GET /api/system/tickets: Get available tickets

Building the Project
bashCopy./mvnw clean install
Running the Application
bashCopy./mvnw spring-boot:run
Deployment

Runs on localhost:8080
Cross-Origin configured for http://localhost:5173

Thread Safety

Synchronized methods in TicketPool
Concurrent thread management
Safe ticket generation and purchasing

Logging

Console logging for system events
Tracks ticket pool status
Monitors thread activities

Error Handling

Graceful thread interruption
Configuration file error management
Runtime configuration validation
