import logo from '../assets/logo.png';
import {Image} from "react-bootstrap";
import '../style/home/DefaultEmptyScreen.css';

function DefaultEmptyScreen() {
  return (
      <div className="empty-space">
          <Image src={logo} className="logo" alt="logo"/>
          <h1>欢迎使用船货匹配模拟系统</h1>
          <p>请点击左侧导航栏项目查看船货信息或新建模拟</p>
      </div>
  );
}

export default DefaultEmptyScreen;