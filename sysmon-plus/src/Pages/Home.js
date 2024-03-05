import React from 'react';
import logo from '../Images/DoTTed Team Logo.png';
import { Route, Routes} from 'react-router-dom';

import CpuSection from './Glance/CpuGlance';
import MemSection from './Glance/MemoryGlance';
import DiskSection from './Glance/DiskGlance';
import Processes from './Processes';
import CpuDetail from './Detail/CpuDetail';
import MemoryDetail from './Detail/MemoryDetail';
import DiskDetail from './Detail/DiskDetail';

const Home = () => {
    return (
      <div className="App">
        <header className="App-header">
          <div className="row">
          <img src={logo} alt="" className="App-logo-header" />
            <h1 className="App-title-header"> SysMon+ </h1>
          </div>
        </header>
  
        <Routes>
          {/* Define routes */}
          <Route path = "/cpu-detail" element={<CpuDetail />} />
          <Route path = "/memory-detail" element={<MemoryDetail />} />
          <Route path = "/disk-detail" element={<DiskDetail />} />
        </Routes>
        
        <div>
          <div className="row">
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
          <Processes />
        </div>
  
        <hr></hr>
  
        <div>
          
        </div>
  
      </div>
    );
  };

  export default Home;