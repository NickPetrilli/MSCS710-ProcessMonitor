import React, { useState, useEffect } from 'react';


const Reports = () => {

    const [cpuAvg15Min, setCpuAvg15Min] = useState({});
    const [cpuAvg1Hour, setCpuAvg1Hour] = useState({});
    const [cpuAvg24Hours, setCpuAvg24Hours] = useState({});

    const [memoryAvg15Min, setMemoryAvg15Min] = useState({});
    const [memoryAvg1Hour, setMemoryAvg1Hour] = useState({});
    const [memoryAvg24Hours, setMemoryAvg24Hours] = useState({});

    const [diskAvg15Min, setDiskAvg15Min] = useState({});
    const [diskAvg1Hour, setDiskAvg1Hour] = useState({});
    const [diskAvg24Hours, setDiskAvg24Hours] = useState({});

    const [diskSpeedAvg15Min, setDiskSpeedAvg15Min] = useState({});
    const [diskSpeedAvg1Hour, setDiskSpeedAvg1Hour] = useState({});
    const [diskSpeedAvg24Hours, setDiskSpeedAvg24Hours] = useState({});

    useEffect(() => {
        // Fetch data from an API endpoint
        const fetchData = () => {
          fetch('http://localhost:8080/api/v1/cpu/avg-util-15min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setCpuAvg15Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg cpu util 15 min:', error);
            });
    
          fetch('http://localhost:8080/api/v1/cpu/avg-util-1hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setCpuAvg1Hour(data);
            })
            .catch(error => {
              console.error('Error fetching avg cpu util 1 hour:', error);
            });

        fetch('http://localhost:8080/api/v1/cpu/avg-util-24hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setCpuAvg24Hours(data);
            })
            .catch(error => {
              console.error('Error fetching avg cpu util 24 hours:', error);
            });

        fetch('http://localhost:8080/api/v1/memory/avg-util-15min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setMemoryAvg15Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg memory util 15 min:', error);
            });

        fetch('http://localhost:8080/api/v1/memory/avg-util-1hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setMemoryAvg1Hour(data);
            })
            .catch(error => {
              console.error('Error fetching avg memory util 1 hour:', error);
            });

        fetch('http://localhost:8080/api/v1/memory/avg-util-24hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setMemoryAvg24Hours(data);
            })
            .catch(error => {
              console.error('Error fetching avg memory util 24 hours:', error);
            });

          fetch('http://localhost:8080/api/v1/disk/avg-util-15min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskAvg15Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk util 15 min:', error);
            });

        fetch('http://localhost:8080/api/v1/disk/avg-util-1hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskAvg1Hour(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk util 1 hour:', error);
            });

        fetch('http://localhost:8080/api/v1/disk/avg-util-24hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskAvg24Hours(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk util 24 hours:', error);
            });

          fetch('http://localhost:8080/api/v1/disk/avg-speeds-15min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskSpeedAvg15Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk speeds 15 min:', error);
            });

        fetch('http://localhost:8080/api/v1/disk/avg-speeds-1hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskSpeedAvg1Hour(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk speeds 1 hour:', error);
            });

        fetch('http://localhost:8080/api/v1/disk/avg-speeds-24hour')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskSpeedAvg24Hours(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk speeds 24 hours:', error);
            });
        }
    
        // Run the function immediately when the component mounts
        fetchData();
    
      }, []); // Empty dependency array means this effect runs once after the first render

    return (
    <>
        <div>
            {cpuAvg15Min !== null ? (
        <div> CPU Average 15 Min: {cpuAvg15Min.utilization}</div>
        ) : (
        <div>No data available for 15 Min Report</div>
        )}
        </div>
    
        <div>
            {cpuAvg1Hour !== null ? (
        <div>CPU Average 1 Hour: {cpuAvg1Hour.utilization}</div>
        ) : (
        <div>No data available for 1 Hour Report</div>
        )}
        </div>

        <div>
            {cpuAvg24Hours !== null ? (
        <div>CPU Average 24 Hours: {cpuAvg24Hours.utilization}</div>
        ) : (
        <div>No data available for 24 Hours Report</div>
        )}
        </div>

        <div>
            {memoryAvg15Min !== null ? (
        <div>Memory Average 15 Min: {memoryAvg15Min.utilization}</div>
        ) : (
        <div>No data available for 15 Min Report</div>
        )}
        </div>
    
        <div>
            {memoryAvg1Hour !== null ? (
        <div>Memory Average 1 Hour: {memoryAvg1Hour.utilization}</div>
        ) : (
        <div>No data available for 1 Hour Report</div>
        )}
        </div>

        <div>
            {memoryAvg24Hours !== null ? (
        <div>Memory Average 24 Hours: {memoryAvg24Hours.utilization}</div>
        ) : (
        <div>No data available for 24 Hours Report</div>
        )}
        </div>

        <div>
            {diskAvg15Min !== null ? (
        <div>Disk Average 15 Min: {diskAvg15Min.utilization}</div>
        ) : (
        <div>No data available for 15 Min Report</div>
        )}
        </div>
    
        <div>
            {diskAvg1Hour !== null ? (
        <div>Disk Average 1 Hour: {diskAvg1Hour.utilization}</div>
        ) : (
        <div>No data available for 1 Hour Report</div>
        )}
        </div>

        <div>
            {diskAvg24Hours !== null ? (
        <div>Disk Average 24 Hours: {diskAvg24Hours.utilization}</div>
        ) : (
        <div>No data available for 24 Hours Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg15Min !== null ? (
        <>
        <div>Disk Read Speed Average 15 Min: {diskSpeedAvg15Min.averageReadSpeed} </div>
        <div>Disk Write Speed Average 15 Min: {diskSpeedAvg15Min.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 15 Min Report</div>
        )}
        </div>
    
        <div>
            {diskSpeedAvg1Hour !== null ? (
        <>
        <div>Disk Read Speed Average 1 Hour: {diskSpeedAvg1Hour.averageReadSpeed} </div>
        <div>Disk Write Speed Average 1 Hour: {diskSpeedAvg1Hour.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 1 Hour Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg24Hours !== null ? (
          <>
        <div>Disk Read Speed Average 24 Hours: {diskSpeedAvg24Hours.averageReadSpeed} </div>
        <div> Disk Write Speed Average 24 Hours: {diskSpeedAvg24Hours.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 24 Hour Report</div>
        )}
        </div>
    </>
    );
  };

  export default Reports;