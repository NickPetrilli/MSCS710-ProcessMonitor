import React, { useState, useEffect } from 'react';
// import { Link } from 'react-router-dom';

const REFRESH_TIME = 10000;


const CpuReports = () => {

  const [selectedOption, setSelectedOption] = useState();

  const handleSelectionChange = (event) => {
      setSelectedOption(event.target.value);
  };

  useEffect(() => {
    if (selectedOption) {
        window.location.href = selectedOption; // Programmatically navigate to the selected route
        // setSelectedOption(""); // Reset selectedOption to trigger useEffect again
    }
}, [selectedOption]);

  const [cpuAvg5Min, setCpuAvg5Min] = useState({});
  const [cpuAvg10Min, setCpuAvg10Min] = useState({});
  const [cpuAvg15Min, setCpuAvg15Min] = useState({});
  const [cpuAvg30Min, setCpuAvg30Min] = useState({});
  const [cpuAvg1Hour, setCpuAvg1Hour] = useState({});
  const [cpuAvg2Hours, setCpuAvg2Hours] = useState({});
  const [cpuAvg4Hours, setCpuAvg4Hours] = useState({});
  const [cpuAvg6Hours, setCpuAvg6Hours] = useState({});
  const [cpuAvg12Hours, setCpuAvg12Hours] = useState({});
  const [cpuAvg24Hours, setCpuAvg24Hours] = useState({});

  useEffect(() => {
     // Fetch data from an API endpoint
     const fetchData = () => {
      fetch('http://localhost:8080/api/v1/cpu/avg-util-5min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg5Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 5 min:', error);
          });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-10min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg10Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 10 min:', error);
      });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-15min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg15Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 15 min:', error);
          });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-30min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg30Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 30 min:', error);
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

      fetch('http://localhost:8080/api/v1/cpu/avg-util-2hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg2Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 2 hour:', error);
          });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-4hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg4Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 4 hour:', error);
          });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-6hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg6Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 6 hour:', error);
          });

      fetch('http://localhost:8080/api/v1/cpu/avg-util-12hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setCpuAvg12Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg cpu util 12 hour:', error);
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
     }

    // Run the function immediately when the component mounts
    fetchData();
    // Set up interval to run the function every 10 seconds
    const intervalId = setInterval(fetchData, REFRESH_TIME);
  
    return () => clearInterval(intervalId);

  }, []);

  return (
    <div>
      <h1 className='detail-title'>
        <select className='disk-selection-menu' onChange={handleSelectionChange} value="/cpu-reports">
            <option className='disk-selection-option' key={"CPU"} value={"/cpu-reports"}>CPU Reports</option>
            <option className='disk-selection-option' key={"Memory"} value={"/memory-reports"}>Memory Reports</option>
            <option className='disk-selection-option' key={"Disk"} value={"/disk-reports"}>Disk Reports</option>
        </select>
        {/* <Link to={selectedOption} style={{ display: 'none' }}></Link> */}
      </h1>

      <table className="detail-table">
        <tbody>
          <tr>
            <td> 5 Minutes: </td>
            <td> {Number(cpuAvg5Min.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 10 Minutes: </td>
            <td> {Number(cpuAvg10Min.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 15 Minutes: </td>
            <td> {Number(cpuAvg15Min.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 30 Minutes: </td>
            <td> {Number(cpuAvg30Min.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 1 Hour: </td>
            <td> {Number(cpuAvg1Hour.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 2 Hours: </td>
            <td> {Number(cpuAvg2Hours.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 4 Hours: </td>
            <td> {Number(cpuAvg4Hours.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 6 Hours: </td>
            <td> {Number(cpuAvg6Hours.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 12 Hours: </td>
            <td> {Number(cpuAvg12Hours.utilization).toFixed(2)}%</td>
          </tr>

          <tr>
            <td> 1 Day: </td>
            <td> {Number(cpuAvg24Hours.utilization).toFixed(2)}%</td>
          </tr>
        </tbody>
      </table>
    </div>
  );

};

const MemReports = () => {

  const [selectedOption, setSelectedOption] = useState("");

  const handleSelectionChange = (event) => {
      setSelectedOption(event.target.value);
  };

  useEffect(() => {
    if (selectedOption) {
        window.location.href = selectedOption; // Programmatically navigate to the selected route
        setSelectedOption(""); // Reset selectedOption to trigger useEffect again
    }
}, [selectedOption]);

  const [memoryAvg5Min, setMemoryAvg5Min] = useState({});
  const [memoryAvg10Min, setMemoryAvg10Min] = useState({});
  const [memoryAvg15Min, setMemoryAvg15Min] = useState({});
  const [memoryAvg30Min, setMemoryAvg30Min] = useState({});
  const [memoryAvg1Hour, setMemoryAvg1Hour] = useState({});
  const [memoryAvg2Hours, setMemoryAvg2Hours] = useState({});
  const [memoryAvg4Hours, setMemoryAvg4Hours] = useState({});
  const [memoryAvg6Hours, setMemoryAvg6Hours] = useState({});
  const [memoryAvg12Hours, setMemoryAvg12Hours] = useState({});
  const [memoryAvg24Hours, setMemoryAvg24Hours] = useState({});

  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/memory/avg-util-5min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setMemoryAvg5Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg memory util 5 min:', error);
            });

      fetch('http://localhost:8080/api/v1/memory/avg-util-10min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg10Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 10 min:', error);
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
        
      fetch('http://localhost:8080/api/v1/memory/avg-util-30min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg30Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 30 min:', error);
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

      fetch('http://localhost:8080/api/v1/memory/avg-util-2hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg2Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 2 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/memory/avg-util-4hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg4Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 4 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/memory/avg-util-6hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg6Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 6 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/memory/avg-util-12hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setMemoryAvg12Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg memory util 12 hours:', error);
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
     
    }

   // Run the function immediately when the component mounts
   fetchData();
   // Set up interval to run the function every 10 seconds
   const intervalId = setInterval(fetchData, REFRESH_TIME);
 
   return () => clearInterval(intervalId);

 }, []);

  return (
    <div>
      <h1 className='detail-title'>
        <select className='disk-selection-menu' onChange={handleSelectionChange} value="/memory-reports">
            <option className='disk-selection-option' key={"CPU"} value={"/cpu-reports"}>CPU Reports</option>
            <option className='disk-selection-option' key={"Memory"} value={"/memory-reports"}>Memory Reports</option>
            <option className='disk-selection-option' key={"Disk"} value={"/disk-reports"}>Disk Reports</option>
        </select>
        {/* <Link to={selectedOption} style={{ display: 'none' }}></Link> */}
      </h1>

      <table className="detail-table">
        <tbody>
          <tr>
            <td> 5 Minutes: </td>
            <td> {Number(memoryAvg5Min.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 10 Minutes: </td>
            <td> {Number(memoryAvg10Min.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 15 Minutes: </td>
            <td> {Number(memoryAvg15Min.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 30 Minutes: </td>
            <td> {Number(memoryAvg30Min.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 1 Hour: </td>
            <td> {Number(memoryAvg1Hour.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 2 Hours: </td>
            <td> {Number(memoryAvg2Hours.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 4 Hours: </td>
            <td> {Number(memoryAvg4Hours.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 6 Hours: </td>
            <td> {Number(memoryAvg6Hours.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 12 Hours: </td>
            <td> {Number(memoryAvg12Hours.utilization).toFixed(2)} %</td>
          </tr>

          <tr>
            <td> 1 Day: </td>
            <td> {Number(memoryAvg24Hours.utilization).toFixed(2)} %</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

const DiskReports = () => {

  const [selectedOption, setSelectedOption] = useState("");

  const handleSelectionChange = (event) => {
      setSelectedOption(event.target.value);
  };

  useEffect(() => {
    if (selectedOption) {
        window.location.href = selectedOption; // Programmatically navigate to the selected route
        // setSelectedOption(""); // Reset selectedOption to trigger useEffect again
    }
}, [selectedOption]);

  const [diskAvg5Min, setDiskAvg5Min] = useState({});
  const [diskAvg10Min, setDiskAvg10Min] = useState({});
  const [diskAvg15Min, setDiskAvg15Min] = useState({});
  const [diskAvg30Min, setDiskAvg30Min] = useState({});
  const [diskAvg1Hour, setDiskAvg1Hour] = useState({});
  const [diskAvg2Hours, setDiskAvg2Hours] = useState({});
  const [diskAvg4Hours, setDiskAvg4Hours] = useState({});
  const [diskAvg6Hours, setDiskAvg6Hours] = useState({});
  const [diskAvg12Hours, setDiskAvg12Hours] = useState({});
  const [diskAvg24Hours, setDiskAvg24Hours] = useState({});

  const [diskSpeedAvg5Min, setDiskSpeedAvg5Min] = useState({});
  const [diskSpeedAvg10Min, setDiskSpeedAvg10Min] = useState({});
  const [diskSpeedAvg15Min, setDiskSpeedAvg15Min] = useState({});
  const [diskSpeedAvg30Min, setDiskSpeedAvg30Min] = useState({});
  const [diskSpeedAvg1Hour, setDiskSpeedAvg1Hour] = useState({});
  const [diskSpeedAvg2Hours, setDiskSpeedAvg2Hours] = useState({});
  const [diskSpeedAvg4Hours, setDiskSpeedAvg4Hours] = useState({});
  const [diskSpeedAvg6Hours, setDiskSpeedAvg6Hours] = useState({});
  const [diskSpeedAvg12Hours, setDiskSpeedAvg12Hours] = useState({});
  const [diskSpeedAvg24Hours, setDiskSpeedAvg24Hours] = useState({});


  useEffect(() => {
    // Fetch data from an API endpoint
    const fetchData = () => {
      fetch('http://localhost:8080/api/v1/disk/avg-util-5min')
            .then(response => response.json())
            .then(data => {
              // Update the JSON object with fetched data
              setDiskAvg5Min(data);
            })
            .catch(error => {
              console.error('Error fetching avg disk util 5 min:', error);
            });

      fetch('http://localhost:8080/api/v1/disk/avg-util-10min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg10Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 10 min:', error);
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

      fetch('http://localhost:8080/api/v1/disk/avg-util-30min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg30Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 30 min:', error);
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

      fetch('http://localhost:8080/api/v1/disk/avg-util-2hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg2Hours(data);
            console.log("Average Disk Util 2 Hours: " + diskAvg2Hours.utilization);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 2 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-util-4hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg4Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 4 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-util-6hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg6Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 6 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-util-12hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskAvg12Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk util 12 hours:', error);
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

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-5min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg5Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 5 min:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-10min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg10Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 10 min:', error);
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

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-30min')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg30Min(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 30 min:', error);
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

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-2hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg2Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 2 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-4hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg4Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 4 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-6hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg6Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 6 hours:', error);
          });

      fetch('http://localhost:8080/api/v1/disk/avg-speeds-12hour')
          .then(response => response.json())
          .then(data => {
            // Update the JSON object with fetched data
            setDiskSpeedAvg12Hours(data);
          })
          .catch(error => {
            console.error('Error fetching avg disk speeds 12 hours:', error);
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
   // Set up interval to run the function every 10 seconds
   const intervalId = setInterval(fetchData, REFRESH_TIME);
 
   return () => clearInterval(intervalId);

 }, []);

  return (
    <div>
      <h1 className='detail-title'>
        <select className='disk-selection-menu' onChange={handleSelectionChange} value="/disk-reports">
            <option className='disk-selection-option' key={"CPU"} value={"/cpu-reports"}>CPU Reports</option>
            <option className='disk-selection-option' key={"Memory"} value={"/memory-reports"}>Memory Reports</option>
            <option className='disk-selection-option' key={"Disk"} value={"/disk-reports"}>Disk Reports</option>
        </select>
        {/* <Link to={selectedOption} style={{ display: 'none' }}></Link> */}
      </h1>

      <table className="detail-table">
        <thead>
          <th>
            <td>Interval</td>
            <td>Utilization</td>
            <td>Read Speed (MB/s)</td>
            <td>Write Speed (MB/s)</td>
          </th>
        </thead>

        <tbody>
          <tr>
            <td> 5 Minutes: </td>
            <td> {Number(diskAvg5Min.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg5Min.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg5Min.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 10 Minutes: </td>
            <td> {Number(diskAvg10Min.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg10Min.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg10Min.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 15 Minutes: </td>
            <td> {Number(diskAvg15Min.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg15Min.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg15Min.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 30 Minutes: </td>
            <td> {Number(diskAvg30Min.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg30Min.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg30Min.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 1 Hour: </td>
            <td> {Number(diskAvg1Hour.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg1Hour.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg1Hour.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 2 Hours: </td>
            <td> {Number(diskAvg2Hours.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg2Hours.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg2Hours.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 4 Hours: </td>
            <td> {Number(diskAvg4Hours.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg4Hours.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg4Hours.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 6 Hours: </td>
            <td> {Number(diskAvg6Hours.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg6Hours.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg6Hours.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 12 Hours: </td>
            <td> {Number(diskAvg12Hours.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg12Hours.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg12Hours.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>

          <tr>
            <td> 1 Day: </td>
            <td> {Number(diskAvg24Hours.utilization).toFixed(2)} %</td>
            <td> {(diskSpeedAvg24Hours.averageReadSpeed / 1000000).toFixed(1)} MB/s </td>
            <td> {(diskSpeedAvg24Hours.averageWriteSpeed / 1000000).toFixed(1)} MB/s </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export { CpuReports };
export { MemReports };
export { DiskReports };

  