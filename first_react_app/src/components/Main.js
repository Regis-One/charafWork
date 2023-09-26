import React from "react"

export default function Main() {
    return (
        <main>
            <h1> Why do we use <img src ="./images/small-react.png" height={"20px"}/>?  </h1>
            <ol className="main_facts">
                <li>
                    <em>Component-Based Architecture:</em> React promotes modular development through reusable components.    
                </li>
                <li>
                    <em>Efficient Virtual DOM:</em> It uses a virtual DOM for optimized UI updates, enhancing performance.
                </li>
                <li>
                    <em>Large Ecosystem and Community:</em> React has a rich ecosystem and an active developer community, providing resources and support.
                </li>
                
            </ol>
        </main>
    )
}