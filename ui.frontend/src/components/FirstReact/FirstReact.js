import React from 'react';
import {MapTo} from '@adobe/aem-react-editable-components';

const firstreactEditConfig = {

    emptyLabel:'myfirstreact',
    isEmpty: function(props) {
    debugger;
        return !props || !props.firstName;
    }
};


function readFirstReactData(props){
           return (
            <div className="first-react">
            first react

            myfirstName={props.firstName};
            </div>
            );
}

export default function FirstReact(props){
 // render nothing if component not configured
        if (firstreactEditConfig.isEmpty(props)) {
            return null;
        }

return readFirstReactData(props);
}


MapTo('aemworldreact/components/first-react')(FirstReact,firstreactEditConfig);
