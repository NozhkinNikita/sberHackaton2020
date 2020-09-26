import React from 'react';
import './App.css';

function App() {
    const jitsiContainerId = "jitsi-container-id";
    const [jitsi, setJitsi] = React.useState({});

    const loadJitsiScript = () => {
        let resolveLoadJitsiScriptPromise = null;

        const loadJitsiScriptPromise = new Promise(resolve => {
            resolveLoadJitsiScriptPromise = resolve;
        });

        const script = document.createElement("script");
        script.src = "https://meet.jit.si/external_api.js";
        script.async = true;
        script.onload = () => resolveLoadJitsiScriptPromise(true);
        document.body.appendChild(script);

        return loadJitsiScriptPromise;
    };

    const initialiseJitsi = async () => {
        if (!window.JitsiMeetExternalAPI) {
            await loadJitsiScript();
        }

        const domain = 'meet.jit.si';
        const options = {
            roomName: 'HAHAHA',
            width: 700,
            height: 700,
            parentNode: document.getElementById(jitsiContainerId),
            interfaceConfigOverwrite: {
                filmStripOnly: false,
                SHOW_JITSI_WATERMARK: false,
            },
            configOverwrite: {
                disableSimulcast: false,
            },
        };


        const _jitsi = new window.JitsiMeetExternalAPI(domain, options);

        setJitsi(_jitsi);
    };

    React.useEffect(() => {
        initialiseJitsi();

        return () => jitsi?.dispose?.();
    }, []);

    return <div id={jitsiContainerId} style={{ height: 720, width: "100%" }} />;
}


export default App;
