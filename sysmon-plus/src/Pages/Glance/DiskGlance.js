import React, { useState, useEffect } from 'react';

const DiskSection = () => {
    const [disk, setDisk] = useState([]);
    const [topProcesses, setTopProcesses] = useState([]);

    useEffect(() => {
        // Fetch disk utilization from API endpoint
        fetch('http://localhost:8080/api/v1/disk')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDisk(data);
          })
          .catch(error => {
            console.error('Error in GET request for general disk information:', error);
          });


        // Fetch top 3 processes (based on disk usage) from an API endpoint
        fetch('http://localhost:8080/api/v1/disk/top-processes')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setTopProcesses(data);
          })
          .catch(error => {
            console.error('Error in GET request for disk top processes:', error);
          });
      }, []); // Empty dependency array means this effect runs once after the first render

    return (
        <div className="section">
            <h2>Disk</h2>
            {disk.length > 0 ? (
                    <p>{disk[0].model}</p>
                ) : (
                    <p>Disk name not available</p>
                )
            }


            <div id='disk-top-processes'>
                {topProcesses.length > 0 ? (
                        topProcesses.map((item, index) => (
                            <pre>{item.name}      {item.diskSpeed} bytes/second</pre>
                        ))
                    ) : (
                    <p>No data available</p>
                )}
            </div>
        </div>
    );
};

export default DiskSection;
