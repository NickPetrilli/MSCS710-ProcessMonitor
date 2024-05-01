<img src="https://github.com/NickPetrilli/MSCS710-ProcessMonitor/assets/70206675/c9314fa4-b781-4701-b85e-d643b5ba3625" alt="SysMon+ Logo" style="width:15%;"> </img>
# SysMon+ (Sis-Mahn-Pl-us)

Welcome to SysMon+, a system and process monitoring tool for Windows-based PCs. SysMon+ allows you to track the usage metrics of different components on your device, 
including your CPU, Memory/RAM, and storage devices; as well as all processes currently running on your system.

## Table of Contents 
  - [Downloading and Installing SysMon+](##Downloading-and-Installing-SysMon+)
    - [Before Downloading SysMon+](#Before-Downloading-SysMon+)
    - [Downloading SysMon+](#Downloading-SysMon+)
    - [Installing SysMon+](#Installing-SysMon+)
      
  - [Getting Started With SysMon+](#Getting-Started-With-SysMon+)

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

  Once all of the software components are installed correctly, you may proceed with the download and installation process

  ### Downloading SysMon+
  To download the latest version of SysMon+, go to the [Releases]() section of the repository page, and find a version you would like to install. 
  
  Once you have found a version you would like to install, select the "SysMon+.zip" folder (NOT THE SOURCE ZIP), and follow your web browser's instructions to perform the download.

  ### Installing SysMon+
  Once the ZIP folder of your SysMon+ version has been successfully downloaded, you will need to extract/unzip the folder to get to its uncompressed version (this is the version that will be run, NOT the compressed version).
  If you do not know how to extract/unzip a file on Windows, please refer to the instructions listed [HERE](https://support.microsoft.com/en-us/windows/zip-and-unzip-files-8d28fa72-f2f9-712f-67df-f80cf89fd4e5)

## Getting Started With SysMon+
  ### Running SysMon+
  To run SysMon+, you will need to run/execute the ```runApplication.bat``` file. The ```runApplication.bat``` file will download all necessary dependencies for the application, configure the application's data-storage database (which is hosted on your local device), and launch the application.
  To run the ```runApplication.bat``` file, open your File Explorer and open the extracted/unzipped version of the SysMon+ folder you downloaded. Inside this folder, double-click on the file to run the ```.bat``` file. Upon running this file, a series of Command Prompt windows will open, which will handle the application configuration process detailed above. Once all Command Prompt windows open, you should see a window which displays that a server has been deployed
  

