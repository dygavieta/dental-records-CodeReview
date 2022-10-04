import {Card} from "react-bootstrap";
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import {FaSave} from 'react-icons/fa';
import {HiDocumentAdd} from 'react-icons/hi';
import {useEffect, useState} from "react";
import axios from "axios";
import PatientToast from "./PatientToast";
import {useParams} from "react-router-dom";
import Contacts from "./Contacts";

const Patient = (props) => {
    const [firstName, setFirstName] = useState("");
    const [lastName, setLastName] = useState("");
    const [middleInitial, setMiddleInitial] = useState("");
    const [sex, setSex] = useState("Male");
    const [age, setAge] = useState("");
    const [dob, setDOB] = useState("");
    const [nationality, setNationality] = useState("");
    const [occupation, setOccupation] = useState("");

    const [show, setShow] = useState(false)
    const [message, setMessage] = useState("")
    let patientId = useParams()


    //Contact Info
    const [region, setRegion] = useState("");
    const [city, setCity] = useState("");
    const [baranggay, setBaranggay] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [emailAddress, setEmailAddress] = useState("");

    const resetField = () => {
        setFirstName("")
        setLastName("")
        setMiddleInitial("")
        setSex("Male")
        setAge("")
        setDOB("")
        setNationality("")
        setOccupation("")
        setRegion("")
        setCity("")
        setBaranggay("")
        setMobileNumber("")
        setEmailAddress("")
    }
    const findPatientId = () => {
        console.log(patientId)
        axios.get("http://localhost:8080/patients/" + patientId.id)
            .then(response => {
                if (response.data !== null) {
                    setFirstName(response.data.firstName)
                    setLastName(response.data.lastName)
                    setMiddleInitial(response.data.middleInitial)
                    setSex(response.data.sex)
                    setAge(response.data.age)
                    setDOB(response.data.dob)
                    setNationality(response.data.nationality)
                    setOccupation(response.data.occupation)
                    setRegion(response.data.contactInfo?.region)
                    setCity(response.data.contactInfo?.city)
                    setBaranggay(response.data.contactInfo?.baranggay)
                    setMobileNumber(response.data.contactInfo?.mobileNumber)
                    setEmailAddress(response.data.contactInfo?.emailAddress)
                }
            })
    }
    const submitPatient = (event) => {
        event.preventDefault();
        const patient = {firstName, lastName, middleInitial, sex, age, dob, nationality, occupation}
        axios.post('http://localhost:8080/patients', patient).then((response) => {
            const contactInfo = { region, city, baranggay, mobileNumber, emailAddress }
            axios.post('http://localhost:8080/patients/' + response.data.patientId + '/contact', contactInfo)
                .then((response) => {
                    if (response.data != null) {
                        resetField()
                        setShow(true)
                        setMessage('Patient Saved Successfully!')
                        setTimeout(() => setShow(false), 3000)
                    } else {
                        setShow(true)
                        setMessage('Failed Saving a Patient!')
                        setTimeout(() => setShow(false), 3000)
                    }
                })
        })

    }
    const [isUpdate, setIsUpdate] = useState([])
    useEffect(() => {
        if (props.method === "Update") {
            findPatientId()
            setIsUpdate(true)
        } else {
            setIsUpdate(false)
        }
    }, []);

    const updatePatient = (e) => {
        e.preventDefault()
        const patient = {firstName, lastName, middleInitial, sex, age, dob, nationality, occupation}
        axios.put("http://localhost:8080/patients/" + patientId.id, patient)
            .then(response => {
                console.log((response.data.firstName))
                const contactInfo = { region, city, baranggay, mobileNumber, emailAddress }
                axios.put('http://localhost:8080/patients/' + patientId.id + '/contact', contactInfo)
                    .then(response =>{
                        if (response.data != null) {
                            setShow(true)
                            setMessage("Patient Updated Successfully!")
                            setTimeout(() => setShow(false), 3000)
                        } else {
                            setShow(true)
                            setMessage("Failed Updating a Patient!")
                            setTimeout(() => setShow(false), 3000)
                        }
                    })
            })
    }
    return (
        <div className="container">
            <div style={show ? {visibility: "visible"} : {visibility: "hidden"}}>
                <PatientToast isOpen={show} message={message} bgColor="#98FB98" fontColor="white"/>
            </div>
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header style={{display: "flex"}}>
                    <HiDocumentAdd size={22}/> {props.title}
                </Card.Header>
                <Card.Body>
                    <Form id="hi">
                        <fieldset  >
                            <div className="basic-form">
                                <Form.Group className="name mb-3">
                                    <Form.Label>First Name</Form.Label>
                                    <Form.Control aria-required="true" type="text" placeholder="First Name"
                                                  value={firstName}
                                                  onChange={(e) => (setFirstName(e.taerget.value))}/>
                                </Form.Group>
                                <Form.Group className="name mb-3">
                                    <Form.Label>Last Name</Form.Label>
                                    <Form.Control type="text" placeholder="Last Name" value={lastName}
                                                  onChange={(e) => (setLastName(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Middle Initial</Form.Label>
                                    <Form.Control type="text" placeholder="Middle Initial" value={middleInitial}
                                                  onChange={(e) => (setMiddleInitial(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Sex</Form.Label>
                                    <Form.Select value={sex} onChange={(e) => (setSex(e.target.value))}>
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                        <option value="Other">Other</option>
                                    </Form.Select>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Age</Form.Label>
                                    <Form.Control type="number" placeholder="Age" min="1" max="999" value={age}
                                                  onChange={(e) => (setAge(e.target.value))}/>
                                </Form.Group>
                            </div>

                            <div style={{display: "flex"}}>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Date of Birth</Form.Label>
                                    <Form.Control type="date" placeholder="(MM-DD-YYYY)" value={dob}
                                                  onChange={(e) => (setDOB(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Nationality</Form.Label>
                                    <Form.Control type="text" placeholder="Nationality" value={nationality}
                                                  onChange={(e) => (setNationality(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Occupation</Form.Label>
                                    <Form.Control type="text" placeholder="Occupation" value={occupation}
                                                  onChange={(e) => (setOccupation(e.target.value))}/>
                                </Form.Group>
                            </div>
                        </fieldset>


                        <hr/>
                        <div>
                            <div style={{display: "flex"}}>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>Region</Form.Label>
                                    <Form.Control type="text" placeholder="Region" value={region}
                                                  onChange={(e) => (setRegion(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>City</Form.Label>
                                    <Form.Control type="text" placeholder="City" value={city}
                                                  onChange={(e) => (setCity(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="name mb-3 px-2">
                                    <Form.Label>Baranggay</Form.Label>
                                    <Form.Control type="text" placeholder="Baranggay" value={baranggay}
                                                  onChange={(e) => (setBaranggay(e.target.value))}/>
                                </Form.Group>
                            </div>
                            <div>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Mobile Number</Form.Label>
                                    <Form.Control type="text" placeholder="Mobile Number" value={mobileNumber}
                                                  onChange={(e) => (setMobileNumber(e.target.value))}/>
                                </Form.Group>
                                <Form.Group className="mb-3 px-2">
                                    <Form.Label>Email Address</Form.Label>
                                    <Form.Control type="email" placeholder="Email Address" value={emailAddress}
                                                  onChange={(e) => (setEmailAddress(e.target.value))}/>
                                </Form.Group>

                            </div>


                        </div>
                        <div className="text-end px-2">
                            <Button variant="primary" type="submit"
                                    onClick={isUpdate ? updatePatient : submitPatient}>
                                <FaSave size={20}/> {props.method === "Update" ? "Update Patient" : "Save Patient"}
                            </Button> {''}
                            <Button variant="info" type="reset" value="Reset" onClick={resetField}>
                                <FaSave size={20}/> Reset Field
                            </Button>
                        </div>

                    </Form>
                </Card.Body>

            </Card>

        </div>
    )
}

export default Patient