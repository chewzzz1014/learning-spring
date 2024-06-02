const React = require('react');
const Employee = require('./employee.js');

module.exports = function EmployeeList(props) {
    const employees = props.employees.map(e =>
        <Employee key={e._links.self.href} employee={e} />
    );

    return (
        <table>
            <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                </tr>
                {employees}
            </tbody>
        </table>
    );
}