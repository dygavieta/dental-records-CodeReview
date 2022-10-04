const Welcome = () => {
    return (
        <div style={{
            width: 1200,
            height: 200,
            margin: "auto",
            borderStyle: "dashed",
            borderRadius: 10,
            backgroundColor: "rgb( 33,37,41)"
        }}>
            <div style={{color: "white", padding: 24, textAlign: "center"}}>
                <h1 style={{fontSize: 64}}> OraCare </h1>
                <h5>Caring for you & your <smile className=""></smile></h5>
                <p style={{color: "gray"}}>Show, Add, Update and Delete Patient</p>
            </div>
        </div>
    )
}
export default  Welcome;