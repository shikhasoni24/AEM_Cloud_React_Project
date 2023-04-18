import React, { useEffect, useState } from 'react'

type ChatGptResponseProps = {
    prompt: string
}
type ResponseProps = {
    res: string
}

export default function ChatGptResponse({ prompt }: ChatGptResponseProps) {
    const [responseData, setResponseData] = useState("");
    const [responseDataStatus, setResponseDataStatus] = useState(false);
    function fetchData(prop: string) {
        let headers = {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json',
        };
        let response = fetch("http://localhost:4502/bin/chat?prompt=" + `${prop}`, {
            method: 'get',
            headers: headers,
            'credentials': 'same-origin'
        })
        response.then(response => response.json())
            .then(json => {
                console.log(json);
                setResponseData(json.answer);
                setResponseDataStatus(true);
            })
    }
    return (
        <div id="answer">
            <div>
            <button className="btn btn-primary" onClick={() => fetchData(`${prompt}`)}>Click To Get Answer</button>
            </div>
           {responseDataStatus&& <h3>Answer of Your Question</h3>}
            <div>{responseData}</div>
        </div>
    )
}
