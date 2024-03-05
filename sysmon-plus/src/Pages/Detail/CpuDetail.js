// cpuSection.js
import React, { useState, useEffect } from 'react';

const CpuDetail = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/cpu')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setJsonData(data);
        })
        .catch(error => {
          console.error('Error fetching data:', error);
        });
    }

    // Run the function immediately when the component mounts
    fetchData();

    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, 10000);

    return () => clearInterval(intervalId);
  }, []); // Empty dependency array means this effect runs once after the first render

  return (
    <div className="CpuDetail">
      <h2>CPU</h2>
      <p> Processor Name: {jsonData.name}</p>
      <p> Processor Speed: {jsonData.speed}</p>
      <p> Processor Max Speed: {jsonData.maxSpeed}</p>
      <p> Cores: {jsonData.numCores}</p>
      <p> Processes: {jsonData.numProcesses}</p>
      <p> Threads: {jsonData.numThreads}</p>
      <p> Utilization: {jsonData.utilization}</p>
    </div>
  );
};

export default CpuDetail;
