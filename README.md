Real-Time Event Ticketing System

Overview

The Real-Time Event Ticketing System is designed to manage ticketing operations efficiently through a backend service and a user-friendly frontend interface. This application offers both a Command-Line Interface (CLI) and a Graphical User Interface (GUI) using React.

Features

Configure ticketing system parameters.

Start and stop ticketing operations dynamically.

Monitor available tickets in real-time.

Access the system through CLI or GUI.

Setup Instructions

Prerequisites

Backend Requirements:

Java 21 or higher

Maven (for dependency management)

Frontend Requirements:

Node.js (version 18 or higher)

npm or Yarn

Backend Setup

Clone the repository:

git clone https://github.com/your-repo-url/RealTimeEventTicketingSystem.git
cd RealTimeEventTicketingSystem-Backend

Build the project:

mvn clean install

Run the backend server:

java -jar target/RealTimeEventTicketingSystem-Backend.jar

The backend will start on http://localhost:8080.

Frontend Setup

Navigate to the frontend directory:

cd event-ticket-frontend

Install dependencies:

npm install

Start the frontend server:

npm run dev

The frontend will start on http://localhost:5173.

CLI Usage

Starting the CLI

Run the CLI application using:

java -jar RealTimeEventTicketingSystem-Backend.jar

CLI Commands

Configure the system:

1

Follow the prompts to enter ticket parameters (e.g., total tickets, release rate, etc.).

Start ticketing operations:

2

This will start the backend system and begin ticket processing.

Stop ticketing operations:

3

Stops the backend system gracefully.

Check system status:

4

Displays whether the system is running or stopped.

Exit the CLI:

5

Stops the backend (if running) and exits the CLI.

GUI Usage

Accessing the GUI

Open your browser and navigate to http://localhost:5173.

GUI Components

Configuration Form:

Enter ticketing parameters and start the system.

Control Panel:

Start or stop the system dynamically.

Ticket Display:

View the number of tickets available in real-time.

Log Display:

Monitor system activity logs.

Troubleshooting

Common Issues

Backend not starting:

Ensure Java 21+ is installed and properly configured in your system PATH.

Check if the port 8080 is free or in use by another application.

Frontend not starting:

Ensure Node.js and npm are installed.

Run npm install before starting the frontend.

CORS Errors:

Ensure the backend allows requests from http://localhost:5173 using @CrossOrigin annotations.

Real-time data not updating:

Verify that the /api/system/tickets endpoint is reachable.

Check browser console logs for errors.

Logs and Debugging

Backend logs are printed in the terminal where the server is running.

Use browser developer tools for frontend debugging.
