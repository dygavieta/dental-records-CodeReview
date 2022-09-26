import './App.css';
import Navbar from './Components/NavBar'
import Welcome from "./Components/Welcome";
import Footer from "./Components/Footer";

import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import PatientList from "./Components/PatientList";
import Patient from "./Components/Patient";
import ViewPatient from "./Components/ViewPatient";
import DentistList from "./Components/DentistList";

function App() {
    return (
        <div className="App">
            <Router>
                <Navbar/>
                <Routes>
                    <Route exact path="/" element={<Welcome/>}/>
                    <Route exact path="/list" element={<PatientList/>}/>
                    <Route exact path="/list/dentist" element={<DentistList/>}/>
                    <Route exact path="/edit/:id" element={<Patient title={"Update Patient"} method="Update"/>}/>
                    <Route exact path="/add" element={<Patient title={"Add Patient"} method="Add"/> }/>
                    <Route exact path="/view" element={<ViewPatient/>}/>
                </Routes>

            </Router>
        </div>
    );
}

export default App;
