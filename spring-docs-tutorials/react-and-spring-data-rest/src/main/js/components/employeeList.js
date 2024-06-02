const React = require('react');
const Employee = require('./employee.js');

module.exports = function EmployeeList(props) {
    function handleNavFirst(e){
    	e.preventDefault();
    	props.onNavigate(props.links.first.href);
    }

    function handleNavPrev(e) {
    	e.preventDefault();
    	props.onNavigate(props.links.prev.href);
    }

    function handleNavNext(e) {
    	e.preventDefault();
    	props.onNavigate(props.links.next.href);
    }

    function handleNavLast(e) {
    	e.preventDefault();
    	props.onNavigate(props.links.last.href);
    }

     const employees = props.employees.map(e =>
            <Employee key={e._links.self.href} employee={e} />
     );

    const navLinks = [];
	if ("first" in props.links) {
		navLinks.push(<button key="first" onClick={handleNavFirst}>&lt;&lt;</button>);
	}
	if ("prev" in props.links) {
		navLinks.push(<button key="prev" onClick={handleNavPrev}>&lt;</button>);
	}
	if ("next" in props.links) {
		navLinks.push(<button key="next" onClick={handleNavNext}>&gt;</button>);
	}
	if ("last" in props.links) {
		navLinks.push(<button key="last" onClick={handleNavLast}>&gt;&gt;</button>);
	}

    return (
        <table>
            <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Description</th>
                    <th></th>
                </tr>
                {employees}
            </tbody>
            <div>
                {navLinks}
            </div>
        </table>
    );
}