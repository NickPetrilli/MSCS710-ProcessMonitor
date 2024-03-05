// memSection.js
import React, { useState, useEffect } from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png';

const MemoryDetail = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/memory')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setJsonData(data);
        })
        .catch(error => {
          console.error('Error fetching memory data:', error);
        });
    }

    // Run the function immediately when the component mounts
    fetchData();

    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, 10000);
      
    return () => clearInterval(intervalId);
  }, []); // Empty dependency array means this effect runs once after the first render

  return (
    <div className="MemoryDetail">
      <div className="row">
        <img src={graphTemplate} alt="" className="graph" />
      </div>
      <h2>Memory</h2>
      <p> Total Memory: {jsonData.totalMemory}</p>
      <p> Available Memory: {jsonData.availableMemory}</p>
      <p> Used Memory: {jsonData.usedMemory}</p>
      <p> Utilization: {jsonData.utilization}</p>
    </div>
  );
};

export default MemoryDetail;
