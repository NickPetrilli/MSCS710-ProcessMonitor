import React, { useState, useEffect } from 'react';

const Processes = () => {
    
    const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

    useEffect(() => {
        // Fetch data from an API endpoint
        fetch('http://localhost:8080/api/v1/process')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setJsonData(data);
          })
          .catch(error => {
            console.error('Error fetching data:', error);
          });
      }, []);

  return (
    <div className="Processes">
        <h1>Processes</h1>
        
        <div>
            <table className="processes-table">
                <thead>
                    <tr className='process-table-row'>
                        <th> Name </th>
                        <th> CPU </th>
                        <th> Memory </th>
                        <th> Disk </th>
                    </tr>
                </thead>
                <tbody>
                {jsonData.length > 0 ? (
                        jsonData.map((jsonData, index) => (
                          <tr key={jsonData.id} className = "processes-row">
                            <td>{jsonData.name}</td>
                            <td>{jsonData.cpuPercentage.toFixed(1)}%</td>
                            <td>{(jsonData.memoryUsageBytes / 1000000).toFixed(1)}MB</td>
                            <td>{(jsonData.diskSpeed / 1000000).toFixed(1)}MB/s</td>
                          </tr>
                        ))
                    ) : (
                    <p>No data available</p>
                )}
                </tbody>
            </table>
        </div>
    </div>
  );
};

export default Processes;
