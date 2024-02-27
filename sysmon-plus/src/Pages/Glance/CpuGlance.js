import React from 'react';
import graphTemplate from '../../Images/Capping GRAPH TEMPLATE.png'
// import UtilTextBox from '../../../utils/getUtilBackgroundColor';

const CpuSection = () => {

  // Directly initializing text
  const text = '80% Utilization';

  const getUtilBackgroundColor = (text) => {
    const percentage = parseInt(text.substring(0, text.indexOf("U")).trim());
    if (percentage < 40) {
      return 'green';
    } else if (percentage >= 40 && percentage < 80) {
      return 'orange';
    } else {
      return 'red';
    }
  };

  const backgroundColor = getUtilBackgroundColor(text);

  return (
    <div className="section-Cpu">
      <h1 className="title">CPU</h1>
      <h4>AMD Ryzen 7 4800H</h4>

      <div>
        {/* Graph side */}
        <div className="row">
          <img src={graphTemplate} alt="" className="graph" />

          {/* Utilization / Top Processes Side */}
          <div className="col">
            <div className="row">
              <div className="utilBox" style={{ backgroundColor }}> {text} </div>
            </div>

            <h4 className="top-processes-table-TITLE"> Top Processes </h4>
            <table className="top-processes-table">
              <tbody>
                <tr>
                  <th> Firefox </th>
                  <th> 13.2% </th>
                </tr>

                <tr>
                  <th> Discord </th>
                  <th> 1.8% </th>
                </tr>

                <tr>
                  <th> Avast </th>
                  <th> 1.1% </th>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CpuSection;
