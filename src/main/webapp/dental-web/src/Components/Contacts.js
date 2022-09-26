import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {FaSave} from "react-icons/fa";
import {useState} from "react";

const Contacts = (props) => {
    //Contact Info
    const [region, setRegion] = useState("");
    const [city, setCity] = useState("");
    const [baranggay, setBaranggay] = useState("");
    const [mobileNumber, setMobileNumber] = useState("");
    const [emailAddress, setEmailAddress] = useState("");

    return (
        <div style={props.show ? {visibility: "visible"} : {visibility: "hidden"}}>
            <h4>Contact Info</h4>
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
    )
}

export default Contacts