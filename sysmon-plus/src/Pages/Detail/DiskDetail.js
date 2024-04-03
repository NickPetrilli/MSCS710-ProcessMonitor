// diskSection.js
import React, { useState, useEffect, useMemo } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { Link } from 'react-router-dom';
import logo from '../../Images/DoTTed Team Logo.png';
import DiskLineChartFromAPI from '../../Charts/DiskLineChartFromAPI';

const DiskDetail = () => {

  const [disks, setDisks] = useState([]);
  const [selectedDiskName, setSelectedDiskName] = useState(() => sessionStorage.getItem('selectedDiskName'));

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = () => {
      // Fetch disk utilization from API endpoint
      fetch('http://localhost:8080/api/v1/disk')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setDisks(data);
        })
        .catch(error => {
          console.error('Error in GET request for general disk information:', error);
        });
    }

  // Run the function immediately when the component mounts
  fetchData();

  // Set up interval to run the function every 10 seconds
  const intervalId = setInterval(fetchData, 10000);

  return () => clearInterval(intervalId);
  }, []); // Empty dependency array means this effect runs once after the first render

  const selectedDisk = useMemo(() => disks.find(disk => disk.name === selectedDiskName), [disks, selectedDiskName]);

  const goBack = () => {
    navigate(-1);
  };

  return (
    <div className="DiskDetail">
      <button onClick={goBack}>Back</button>
      
      <h2>Disk</h2>
      {
        selectedDisk ? (
          <div>
            <DiskLineChartFromAPI diskName={selectedDisk.name} view='detail' />
            <div>
              <p>Name: {selectedDisk.name}</p>
              <p>Model: {selectedDisk.model}</p>
              <p>Total Swap Bytes: {selectedDisk.swapTotal}</p>
              <p>Used Swap Bytes: {selectedDisk.swapUsed}</p>
              <p>Swap Utilization: {selectedDisk.swapUtilization}</p>
              <p>Total Read Bytes: {selectedDisk.totalReadBytes}</p>
              <p>Total Write Bytes: {selectedDisk.totalWriteBytes}</p>
              <p>Read Speed: {selectedDisk.readSpeed}</p>
              <p>Write Speed: {selectedDisk.writeSpeed}</p>
              <p>Utilization: {selectedDisk.utilization}</p>
            </div>
          </div>
        ) : (
          <p>No disk selected</p>
        )
      }
      {/* If we wanted to display all disks. */}
      {
      /*
      disks.map((disk, index) => (
        <div key={index}>
          <p>Name: {disk.name}</p>
          <p>Model: {disk.model}</p>
          <p>Total Swap Bytes: {disk.swapTotal}</p>
          <p>Used Swap Bytes: {disk.swapUsed}</p>
          <p>Swap Utilization: {disk.swapUtilization}</p>
          <p>Total Read Bytes: {disk.totalReadBytes}</p>
          <p>Total Write Bytes: {disk.totalWriteBytes}</p>
          <p>Read Speed: {disk.readSpeed}</p>
          <p>Write Speed: {disk.writeSpeed}</p>
          <p>Utilization: {disk.utilization}</p>
        </div>
      ))
      */
      }
    </div>
  );
};

export default DiskDetail;
