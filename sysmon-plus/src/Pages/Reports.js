import React, { useState, useEffect } from 'react';


const Reports = () => {

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
        const intervalId = setInterval(fetchData, 10000);
      
        return () => clearInterval(intervalId);
      }, []);
      
    return (
    <>
        <div>
            {cpuAvg5Min !== null ? (
        <div> CPU Average 5 Min: {cpuAvg5Min.utilization}</div>
        ) : (
        <div>No data available for 5 Min Report</div>
        )}
        </div>

        <div>
            {cpuAvg10Min !== null ? (
        <div> CPU Average 10 Min: {cpuAvg10Min.utilization}</div>
        ) : (
        <div>No data available for 10 Min Report</div>
        )}
        </div>

        <div>
            {cpuAvg15Min !== null ? (
        <div> CPU Average 15 Min: {cpuAvg15Min.utilization}</div>
        ) : (
        <div>No data available for 15 Min Report</div>
        )}
        </div>

        <div>
            {cpuAvg30Min !== null ? (
        <div> CPU Average 30 Min: {cpuAvg30Min.utilization}</div>
        ) : (
        <div>No data available for 30 Min Report</div>
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
            {cpuAvg2Hours !== null ? (
        <div>CPU Average 2 Hours: {cpuAvg2Hours.utilization}</div>
        ) : (
        <div>No data available for 2 Hours Report</div>
        )}
        </div>

        <div>
            {cpuAvg4Hours !== null ? (
        <div>CPU Average 4 Hours: {cpuAvg4Hours.utilization}</div>
        ) : (
        <div>No data available for 4 Hours Report</div>
        )}
        </div>

        <div>
            {cpuAvg6Hours !== null ? (
        <div>CPU Average 6 Hours: {cpuAvg6Hours.utilization}</div>
        ) : (
        <div>No data available for 6 Hours Report</div>
        )}
        </div>

        <div>
            {cpuAvg12Hours !== null ? (
        <div>CPU Average 12 Hours: {cpuAvg12Hours.utilization}</div>
        ) : (
        <div>No data available for 12 Hours Report</div>
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
            {memoryAvg5Min !== null ? (
        <div>Memory Average 5 Min: {memoryAvg5Min.utilization}</div>
        ) : (
        <div>No data available for 5 Min Report</div>
        )}
        </div>

        <div>
            {memoryAvg10Min !== null ? (
        <div>Memory Average 10 Min: {memoryAvg10Min.utilization}</div>
        ) : (
        <div>No data available for 10 Min Report</div>
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
            {memoryAvg30Min !== null ? (
        <div>Memory Average 30 Min: {memoryAvg30Min.utilization}</div>
        ) : (
        <div>No data available for 30 Min Report</div>
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
            {memoryAvg2Hours !== null ? (
        <div>Memory Average 2 Hours: {memoryAvg2Hours.utilization}</div>
        ) : (
        <div>No data available for 2 Hours Report</div>
        )}
        </div>

        <div>
            {memoryAvg4Hours !== null ? (
        <div>Memory Average 4 Hours: {memoryAvg4Hours.utilization}</div>
        ) : (
        <div>No data available for 4 Hours Report</div>
        )}
        </div>

        <div>
            {memoryAvg6Hours !== null ? (
        <div>Memory Average 6 Hours: {memoryAvg6Hours.utilization}</div>
        ) : (
        <div>No data available for 6 Hours Report</div>
        )}
        </div>

        <div>
            {memoryAvg12Hours !== null ? (
        <div>Memory Average 12 Hours: {memoryAvg12Hours.utilization}</div>
        ) : (
        <div>No data available for 12 Hours Report</div>
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
            {diskAvg5Min !== null ? (
        <div>Disk Average 5 Min: {diskAvg5Min.utilization}</div>
        ) : (
        <div>No data available for 5 Min Report</div>
        )}
        </div>

        <div>
            {diskAvg10Min !== null ? (
        <div>Disk Average 10 Min: {diskAvg10Min.utilization}</div>
        ) : (
        <div>No data available for 10 Min Report</div>
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
            {diskAvg30Min !== null ? (
        <div>Disk Average 30 Min: {diskAvg30Min.utilization}</div>
        ) : (
        <div>No data available for 30 Min Report</div>
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
            {diskAvg2Hours !== null ? (
        <div>Disk Average 2 Hour: {diskAvg2Hours.utilization}</div>
        ) : (
        <div>No data available for 1 Hour Report</div>
        )}
        </div>

        <div>
            {diskAvg4Hours !== null ? (
        <div>Disk Average 4 Hours: {diskAvg4Hours.utilization}</div>
        ) : (
        <div>No data available for 4 Hours Report</div>
        )}
        </div>

        <div>
            {diskAvg6Hours !== null ? (
        <div>Disk Average 6 Hours: {diskAvg6Hours.utilization}</div>
        ) : (
        <div>No data available for 6 Hours Report</div>
        )}
        </div>

        <div>
            {diskAvg12Hours !== null ? (
        <div>Disk Average 12 Hour: {diskAvg12Hours.utilization}</div>
        ) : (
        <div>No data available for 12 Hours Report</div>
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
            {diskSpeedAvg5Min !== null ? (
        <>
        <div>Disk Read Speed Average 5 Min: {diskSpeedAvg5Min.averageReadSpeed} </div>
        <div>Disk Write Speed Average 5 Min: {diskSpeedAvg5Min.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 5 Min Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg10Min !== null ? (
        <>
        <div>Disk Read Speed Average 10 Min: {diskSpeedAvg10Min.averageReadSpeed} </div>
        <div>Disk Write Speed Average 10 Min: {diskSpeedAvg10Min.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 10 Min Report</div>
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
            {diskSpeedAvg30Min !== null ? (
        <>
        <div>Disk Read Speed Average 30 Min: {diskSpeedAvg30Min.averageReadSpeed} </div>
        <div>Disk Write Speed Average 30 Min: {diskSpeedAvg30Min.averageWriteSpeed}</div>
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
            {diskSpeedAvg2Hours !== null ? (
        <>
        <div>Disk Read Speed Average 2 Hours: {diskSpeedAvg2Hours.averageReadSpeed} </div>
        <div>Disk Write Speed Average 2 Hours: {diskSpeedAvg2Hours.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 2 Hours Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg4Hours !== null ? (
        <>
        <div>Disk Read Speed Average 4 Hours: {diskSpeedAvg4Hours.averageReadSpeed} </div>
        <div>Disk Write Speed Average 4 Hours: {diskSpeedAvg4Hours.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 4 Hours Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg6Hours !== null ? (
        <>
        <div>Disk Read Speed Average 6 Hours: {diskSpeedAvg6Hours.averageReadSpeed} </div>
        <div>Disk Write Speed Average 6 Hours: {diskSpeedAvg6Hours.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 6 Hours Report</div>
        )}
        </div>

        <div>
            {diskSpeedAvg12Hours !== null ? (
        <>
        <div>Disk Read Speed Average 12 Hours: {diskSpeedAvg12Hours.averageReadSpeed} </div>
        <div>Disk Write Speed Average 12 Hours: {diskSpeedAvg12Hours.averageWriteSpeed}</div>
        </>
        ) : (
        <div>No data available for 12 Hours Report</div>
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