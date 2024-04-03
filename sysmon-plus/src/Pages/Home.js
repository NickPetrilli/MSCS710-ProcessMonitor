import React from 'react';
import logo from '../Images/DoTTed Team Logo.png';

import CpuSection from './Glance/CpuGlance';
import MemSection from './Glance/MemoryGlance';
import DiskSection from './Glance/DiskGlance';


const Home = () => {
    return (
      <div className="App">
        <div>
          <div className="home-top-glance-row">
            <CpuSection />
            <MemSection />
          </div>
        </div>
  
        <hr></hr>
  
        <div className="center-container">
          <DiskSection />
        </div>
  
        <hr></hr>
  
        <div>
          
        </div>
  
      </div>
    );
  };

  export default Home;