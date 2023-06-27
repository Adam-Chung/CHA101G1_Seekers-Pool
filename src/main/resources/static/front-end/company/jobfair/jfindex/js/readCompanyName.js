axios.post('/SeekerPool/FrontEnd/ShowCompanyMemberInfo')
    .then(response => {
        const data = response.data;
        console.log("Received data: ", data);

        document.getElementById('brand-name').innerHTML = data.comName;
    })
    .catch(error => {
        console.log('Error: ' + error);
    });