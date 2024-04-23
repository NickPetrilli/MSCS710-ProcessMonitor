import logo from '../Images/SysMon+ Logo Dark.png';
import { Link } from 'react-router-dom';

const reportLinkStyle = {
        margin: '2%'
};


const Header = () => {
    return (
        <header className="App-header">
        <Link to="/"> <img src={logo} alt="" className="App-logo-header" /> </Link>
        <Link to="/reports" style={reportLinkStyle}> Click for Reports </Link>
        </header>
    )
};

export default Header;