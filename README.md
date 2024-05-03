<img src="https://github.com/NickPetrilli/MSCS710-ProcessMonitor/assets/70206675/c9314fa4-b781-4701-b85e-d643b5ba3625" alt="SysMon+ Logo" style="width:15%;"> </img>
# SysMon+ (Sis-Mahn-Plus)

Welcome to SysMon+, a system and process monitoring tool for Windows-based PCs. SysMon+ allows you to track the usage metrics of different components on your device, 
including your CPU, Memory/RAM, and storage devices; as well as all processes currently running on your system.

## Table of Contents 
  - [Downloading and Installing SysMon+](##Downloading-and-Installing-SysMon+)
    - [Before Downloading SysMon+](#Before-Downloading-SysMon+)
    - [Downloading SysMon+](#Downloading-SysMon+)
    - [Installing SysMon+](#Installing-SysMon+)
      
  - [Getting Started With SysMon+](#Getting-Started-With-SysMon+)
    - [Running SysMon+](#Running-Sysmon+)
    - [The "Secret" Terminal Windows](#The-Secret-Terminal-Windows)
    - [Closing / Stopping SysMon+](#closing--stopping-sysmon+)
    - [Running SysMon+ In The Background](#Running-SysMon+-In-The-Background)

  -[SysMon+ Features](#SysMon+-Features)
    - [Homepage / Glance View](#homepage--glance-view)
    - [Detail View](#Detail-View)
    - [Processes View](#Processes-View)
    - [Reports View](#Reports-View)

## Downloading and Installing SysMon+
  ### Before Downloading SysMon+
  To install the latest version of SysMon+, your system must meet the following requirements:
  
  1. Your system must be running Windows 11 OR Windows 10 (while not confirmed, running on any older versions of Windows has not been tested - if attempting to download/install on Windows 7 or earlier, ⚠️⚠️⚠️ PLEASE PROCEED AT YOUR OWN RISK ⚠️⚠️⚠️)
  2. Your system has a web browser installed AND a default web browser is configured in your Windows setting
      - If you are unsure if you have a default web browser configured, click [HERE](https://support.microsoft.com/en-us/windows/change-your-default-browser-in-windows-020c58c6-7d77-797a-b74e-8f07946c5db6) to learn how to set it
  3. Your system must have Java Runtime Environment (JRE) 8 or higher installed
      - If you do not have Java installed on your system, you can download the latest version of Java [HERE](https://www.oracle.com/java/technologies/downloads/)
      - NOTE: You DO NOT need to install the Java Development Kit (JDK). Scroll down on this page until you access the JRE "X" section, and select one of the installers to perform the download and install of the JRE onto your device.
  4. Your system must have Node.js version 20.11.1 or higher installed
      - If you do not have Node.js installed on your system, you can download the latest version of Node.js [HERE](https://nodejs.org/en/download)

  Once all of the software components are installed correctly, you may proceed with the download and installation process.

  ### Downloading SysMon+
  To download the latest version of SysMon+, go to the [Releases]() section of the repository page, and find a version you would like to install. 
  
  Once you have found a version you would like to install, select the "SysMon+.zip" folder (NOT THE SOURCE ZIP), and follow your web browser's instructions to perform the download.

  ### Installing SysMon+
  Once the ZIP folder of your SysMon+ version has been successfully downloaded, you will need to extract/unzip the folder to get to its uncompressed version (this is the version that will be run, NOT the compressed version).
  If you do not know how to extract/unzip a file on Windows, please refer to the instructions listed [HERE](https://support.microsoft.com/en-us/windows/zip-and-unzip-files-8d28fa72-f2f9-712f-67df-f80cf89fd4e5).

## Getting Started With SysMon+
  ### Running SysMon+
  To run SysMon+, you will need to run/execute the ```runApplication.bat``` file. The ```runApplication.bat``` file will download all necessary dependencies for the application, configure the application's data-storage database (which is hosted on your local device), and launch the application.
  To run the ```runApplication.bat``` file, open your File Explorer and open the extracted/unzipped version of the SysMon+ folder you downloaded. Inside this folder, double-click on the file to run the ```.bat``` file. Upon running this file, a series of Command Prompt windows will open, which will handle the application configuration process detailed above. Once all Command Prompt windows open, you should see a window which displays that a server has been deployed. This will trigger for a browser window to open, which will display SysMon+'s UI

  NOTE: When running SysMon+ for the first time, no data may load on the UI. Please wait around 10-15 seconds for SysMon+ to begin to gather data for your device before interacting with the UI.

  ### The *"Secret"* Terminal Windows
  When running SysMon+, a series of terminal windows will be launched that will handle SysMon+'s backend services. The windows that are opened are as follows:

 ⚠️⚠️⚠️ NOTE: DO NOT CLOSE THESE WINDOWS WHILE ACCESSING SYSMON+'S UI, as doing so will cause SysMon+ to stop functioning as intended. ⚠️⚠️⚠️
  
  1. The "Backend" Window
  
      This window will start the Java-based, Springboot-powered backend service which will handle the collection of all system metric data, including data retrieval and storage of said data to the application's local database.
  
  2. The "Get Server Ready" Window

      This window will display logging messages for the install of the ```npm install serve```, a Node/NPM-based package which will allow for SysMon+'s UI to run locally without the need for an external server via the Internet. The window will also display logging message for the execution of ```serve -s build```, a command which will trigger the start of SysMon+'s UI on the local server which is installed on your device (don't worry, we will not be taking your SSN from this server :) )

  ### Closing / Stopping SysMon+
  To close SysMon+, close the UI from your web browser, then close the terminal windows to stop SysMon+'s backend metrics collection.

  ### Running SysMon+ In The Background
  If you would like to run SysMon+ in the background without viewing the UI, you can close the UI and keep all of the terminal windows open (you can have them minimized, but they cannot be closed entirely). This will allow SysMon+'s backend metrics collection to continue to collect metrics.

  If you would like to reopen the UI, enter the URL given within the green window on the "" backend terminal window into your web browser.

## SysMon+ Features
  ### Homepage / Glance View
  When first loading into SysMon+, you will be greeted by the "Glance View", a view which displays the basic utilization for the hardware components which are monitored SysMon+.

  For each of the hardware components, the following metrics will be displayed:
  - Component Utilization History (up to the last 20 data collections, via a graph)
  - Last-Tracked Component Utilization (as a percentage)
  - Top 3 Processes Utilizing "X" Component

  ### Detail View
  Clicking on any of the hardware component name's - "CPU", "Memory", or "Disk" - or any hardware component's utilization value on the "Glance View" which redirect you to that component's "Detail View".

  The "Detail View" will open an expanded view of the metrics for each hardware component. Each hardware component will have their own "Detail View" page, with each hardware component displaying all of the following metrics:
  - Component Utilization History (up to the last 20 data collections, via a graph)
  - Last-Tracked Component Utilization (as a percentage)
  - Hardware Component Information/Additional Metrics Table
    - Each hardware component will have different information displayed within the "Additional Metrics" Table
      - CPU: Current Clock Speed (in gigahertz), Max Clock Speed (in gigahertz), Number of CPU Cores, Number of Active Processes, Number of Active Threads
      - Memory: Total Memory Used (in megabytes), Total Available Installed (in megabytes), Total Used Memory (in megabytes)
      - Disk: Read Speed (in kilobytes per second), Write Speed (in kilobytes per second), Total Swap Used (in megabytes), Total Swap (in megabytes), Swap Utilization (in percent)
  
  ### Processes View
  Clicking on the "Top Processes" Table within any of the hardware components on the "Glance View" will open the "Process View" for that particular component.

  The "Processes View" will display the process utilization for the "top" 50 processes on the system that are utilizing one of your system's hardware component (CPU/Memory/Disk). The "top" processes are determined by the hardware component that is sorting the table on the "Processes View", which is signified by the column with the darker color.

  When accessing the "Processes View", the sort of the processes will be in descending order for the hardware component you accessed the page from. For example, if you accessed the "Processes View" from the Disk's "Top Processes" table, then the "Processes View" will sort the table in descending order (most-utilized at the top, least-utilized at the bottom) based on the disk utilization of all monitored processes.

  Once on the "Processes View", you can change the sort of the table by clicking the name of one of the hardware components located in the top row of the table. Selecting the component which is already sorting the table will reverse the sort of table based on the same hardware component (IE: if you are sorting the processes based on disk utilization, and the sort is in descending order, cliking on the "Disk" will reload the table based on an ascending order: least-utilized at the top of the table, most-utilized at the bottom of the table).

  ### Reports View
  To access the "Reports View", click on the "Reports" button from the top-right of the window.

  The "Reports View" shows the average utilization for all hardware components at selected time intervals (5 minutes, 10 minutes, 15 minutes, 30 minutes, 1 hour, 2 hours, 4 hours, 6 hours, 12 hours, 1 day).

  When loading into the "Reports View", the view will default to the report for your device's CPU. To change the hardware component report to view, select the component type from the dropdown menu.




  

