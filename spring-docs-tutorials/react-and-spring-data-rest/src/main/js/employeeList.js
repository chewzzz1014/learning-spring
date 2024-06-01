const React = require('react');
const Employee = require('./employee.js');

module.exports = function EmployeeList({employees}) {
    const employees = employees.map(e =>
        <Employee key={employee._links.self.href} employee={e} />
    );

    return (
        <table>
            <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                </tr>
            </tbody>
        </table>
    );
}