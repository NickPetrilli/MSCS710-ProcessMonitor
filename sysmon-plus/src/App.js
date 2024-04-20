import './App.css';

// App.js
import React from 'react';
import { Route, Routes} from 'react-router-dom';

import Home from './Pages/Home';
import CpuDetail from './Pages/Detail/CpuDetail';
import MemoryDetail from './Pages/Detail/MemoryDetail';
import DiskDetail from './Pages/Detail/DiskDetail';
import Processes from './Pages/Processes';
import { CpuProcesses } from './Pages/Processes';
import { CpuProcessesAscending } from './Pages/Processes';
import { MemoryProcesses } from './Pages/Processes';
import { MemoryProcessesAscending } from './Pages/Processes';
import { DiskProcesses } from './Pages/Processes';
import { DiskProcessesAscending } from './Pages/Processes';
import Reports from './Pages/Reports';

import Header from './Pages/Header';
import Footer from './Pages/Footer';

const App = () => {
  return (
      <div className="App">

        <Header />

        <Routes>
          {/* Define routes */}
          <Route path = "/" exact element={<Home />} />
          <Route path = "/cpu-detail" element={<CpuDetail />} />
          <Route path = "/memory-detail" element={<MemoryDetail />} />
          <Route path = "/disk-detail" element={<DiskDetail />} />
          <Route path = "/processes" element={<Processes />} />
          <Route path = "/cpu-processes" element={<CpuProcesses />} />
          <Route path = "/cpu-processes-asc" element={<CpuProcessesAscending />} />
          <Route path = "/memory-processes" element={<MemoryProcesses />} />
          <Route path = "/memory-processes-asc" element={<MemoryProcessesAscending />} />
          <Route path = "/disk-processes" element={<DiskProcesses />} />
          <Route path = "/disk-processes-asc" element={<DiskProcessesAscending />} />
          <Route path = "/reports" element={<Reports />} />
        </Routes>

        <Footer />
      </div>
    );
};

export default App;
