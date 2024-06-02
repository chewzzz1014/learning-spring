const {useState, useEffect} = require('react');
const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');
const follow = require('./follow');
const EmployeeList = require('./components/employeeList.js');
const CreateDialog = require('./components/CreateDialog.js');

const root = '/api';

export default function App() {
    const [employees, setEmployees] = useState([]);
    const [attributes, setAttributes] = useState([]);
    const [pageSize, setPageSize] = useState(2);
    const [links, setLinks] = useState({});

    function onCreate(newEmployee) {
        follow(client, root, ['employees']).then(employeeCollection => {
            return client({
                method: 'POST',
                path: employeeCollection.entity._links.self.href,
                entity: newEmployee,
                headers: {'Content-Type': 'application/json'}
            });
        }).then(response => {
                return follow(client, root, [
                		{rel: 'employees', params: {'size': pageSize}}
                ]);
        }).done(response => {
            if (typeof response.entity._links.last !== "undefined") {
                onNavigate(response.entity._links.last.href);
            } else {
            	onNavigate(response.entity._links.self.href);
            }
        });
    }

    function onNavigate(navUri) {
        client({method: 'GET', path: navUri}).done(employeeCollection => {
            setEmployees(employeeCollection.entity._embedded.employees);
            setAttributes(attributes);
            setPageSize(pageSize);
            setLinks(employeeCollection.entity_links);
        });
    }

    function loadFromServer(pageSize) {
        let employeeCollectionResult = {};

        follow(client, root, [
            {rel: 'employees', params: {size: pageSize}}
        ]).then(employeeCollection => {
            employeeCollectionResult = employeeCollection;
            return client({
                method: 'GET',
                path: employeeCollection.entity._links.profile.href,
                headers: {'Accept': 'application/schema+json'}
            }).then(schema => {
                return schema;
            });
        }).done(schema => {
            setEmployees(employeeCollectionResult.entity._embedded.employees);
            setAttributes(Object.keys(schema.entity.properties));
            setPageSize(pageSize);
            setLinks(employeeCollectionResult.entity._links);
            console.log(links)
        });
    }

    useEffect(() => {
        client({
            method: 'GET',
            path: '/api/employees',
        }).done(response => {
            console.log(response)
            loadFromServer(pageSize);
        });
    }, []);

    return (
        <div>
            <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
            <EmployeeList employees={employees} onNavigate={onNavigate} links={links} />
        </div>
    );
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)