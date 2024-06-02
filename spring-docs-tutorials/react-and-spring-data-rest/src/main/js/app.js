const {useState, useEffect} = require('react');
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const EmployeeList = require('./employeeList.js');

export default function App() {
    const [employees, setEmployees] = React.useState([]);

    useEffect(() => {
        client({
            method: 'GET',
            path: '/api/employees',
        }).done(response => {
            console.log(response)
            setEmployees(response.entity._embedded.employees);
        });
    }, []);

    return (
        <EmployeeList employees={employees} />
    );
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)