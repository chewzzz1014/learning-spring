const React = require('react');

module.exports = function Employee({employee}) {

   return (
        <tr>
    		<td>{employee.firstName}</td>
    		<td>{employee.lastName}</td>
    		<td>{employee.description}</td>
    	</tr>
   )
}