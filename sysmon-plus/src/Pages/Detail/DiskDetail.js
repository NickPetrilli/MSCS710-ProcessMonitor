// diskSection.js
import React, { useState, useEffect } from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png';

const DiskDetail = () => {

  const [disks, setDisks] = useState([]);
  
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

  return (
    <div className="DiskDetail">
      <div className="row">
        <img src={graphTemplate} alt="" className="graph" />
      </div>
      <h2>Disk</h2>
      {disks.map((disk, index) => (
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
      ))}
    </div>
  );
};

export default DiskDetail;
