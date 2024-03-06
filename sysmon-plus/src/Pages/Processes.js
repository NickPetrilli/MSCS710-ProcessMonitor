import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

//Process class will be broken up into CpuProcesses, MemoryProcesses, and DiskProcesses
//Depending on which "Top Processes" the user clicks, processes will be sorted by that metric

const CpuProcesses = () => {
    
  const [cpuProcessData, setCpuProcessData] = useState({});

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = () => {
      // Fetch data from an API endpoint
      fetch('http://localhost:8080/api/v1/cpu/processes')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setCpuProcessData(data);
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
  }, []);

  const goBack = () => {
    navigate(-1);
  };

return (
  <div className="CpuProcesses">
    <button onClick={goBack}>Back</button>
      <h1>CPU Processes</h1>
      
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
              {cpuProcessData.length > 0 ? (
                      cpuProcessData.map((process, index) => (
                        <tr key={process.id} className = "processes-row">
                          <td>{process.name}</td>
                          <td>{process.cpuPercentage.toFixed(1)}%</td>
                          <td>{(process.memoryUsageBytes / 1000000).toFixed(1)}MB</td>
                          <td>{(process.diskSpeed / 1000000).toFixed(1)}MB/s</td>
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

const MemoryProcesses = () => {
    
  const [memProcessData, setMemProcessData] = useState({});

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = () => {
      // Fetch data from an API endpoint
      fetch('http://localhost:8080/api/v1/memory/processes')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setMemProcessData(data);
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
  }, []);

  const goBack = () => {
    navigate(-1);
  };

return (
  <div className="MemoryProcesses">
    <button onClick={goBack}>Back</button>
      <h1>Memory Processes</h1>
      
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
              {memProcessData.length > 0 ? (
                      memProcessData.map((process, index) => (
                        <tr key={process.id} className = "processes-row">
                          <td>{process.name}</td>
                          <td>{process.cpuPercentage.toFixed(1)}%</td>
                          <td>{(process.memoryUsageBytes / 1000000).toFixed(1)}MB</td>
                          <td>{(process.diskSpeed / 1000000).toFixed(1)}MB/s</td>
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

const DiskProcesses = () => {
    
  const [diskProcessData, setDiskProcessData] = useState({});

  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = () => {
      // Fetch data from an API endpoint
      fetch('http://localhost:8080/api/v1/memory/processes')
        .then(response => response.json())
        .then(data => {
          // Update the JSON object with fetched data
          setDiskProcessData(data);
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
  }, []);

  const goBack = () => {
    navigate(-1);
  };

return (
  <div className="DiskProcesses">
    <button onClick={goBack}>Back</button>
      <h1>Disk Processes</h1>
      
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
              {diskProcessData.length > 0 ? (
                      diskProcessData.map((process, index) => (
                        <tr key={process.id} className = "processes-row">
                          <td>{process.name}</td>
                          <td>{process.cpuPercentage.toFixed(1)}%</td>
                          <td>{(process.memoryUsageBytes / 1000000).toFixed(1)}MB</td>
                          <td>{(process.diskSpeed / 1000000).toFixed(1)}MB/s</td>
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


const Processes = () => {
    
    const [jsonData, setJsonData] = useState({}); // Initialize an empty JSON object

    useEffect(() => {
      const fetchData = () => {
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
      }

      // Run the function immediately when the component mounts
      fetchData();

      // Set up interval to run the function every 10 seconds
      const intervalId = setInterval(fetchData, 10000);
      
      return () => clearInterval(intervalId);
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
export { CpuProcesses };
export { MemoryProcesses };
export { DiskProcesses };
