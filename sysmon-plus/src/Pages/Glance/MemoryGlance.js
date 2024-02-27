// memSection.js
import React, { useState, useEffect } from 'react';

const MemSection = () => {

  const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

  useEffect(() => {
    // Fetch data from an API endpoint
    fetch('http://localhost:8080/api/v1/memory')
      .then(response => response.json())
      .then(data => {
        // Update the JSON object with fetched data
        setJsonData(data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, []); // Empty dependency array means this effect runs once after the first render



  return (
    <div className="section">
      <h2>Memory</h2>
      <p>Total Memory: {(jsonData.totalMemory / 1000000000).toFixed(2)} GB</p>
      <p>Available Memory: {(jsonData.availableMemory / 1000000000).toFixed(2)} GB</p>
      <p>Used Memory: {(jsonData.usedMemory / 1000000000).toFixed(2)} GB</p>
      <p>Utilization: {Math.round(jsonData.utilization)}%</p>
    </div>
  );
};

export default MemSection;
