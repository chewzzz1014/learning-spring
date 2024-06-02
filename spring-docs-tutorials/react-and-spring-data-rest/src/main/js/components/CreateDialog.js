const ReactDOM = require('react-dom');
const {useRef} = require('react');

module.exports = function CreateDialog({attributes, onCreate}) {
    const dialogInputs = useRef({});

    function handleSubmit(e) {
        e.preventDefault();
        const newEmployee = {};
        attributes.forEach(attribute => {
            newEmployee[attributes] = dialogInputs.current[attribute].value.trim();
        });

        onCreate(newEmployee);

        attributes.forEach(attribute => {
            dialogInputs.current[attribute].value = '';
        });

        window.location = '#';
    }

    const inputs = attributes.map(attribute =>
        <p key={attribute}>
        	<input type="text" placeholder={attribute} ref={(e) => dialogInputs.current[attribute] = e} className="field"/>
        </p>
    );

    return (
        <div>
            <a href="#createEmployee">Create</a>
            <div id="createEmployee" className="modalDialog">
                <div>
                    <a href="#" title="Close" className="close">X</a>
                    <h2>Create new employee</h2>
                    <form>
                        {inputs}
                        <button onClick={handleSubmit}>Create</button>
                    </form>
                </div>
            </div>
        </div>
    )
}