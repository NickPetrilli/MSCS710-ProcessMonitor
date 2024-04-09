import logo from '../Images/SysMon+ Logo Dark.png';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header className="App-header">
        <Link to="/"> <img src={logo} alt="" className="App-logo-header" /> </Link>
        </header>
    )
};

export default Header;