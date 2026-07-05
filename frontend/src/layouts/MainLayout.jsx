import { Outlet } from "react-router-dom";

function MainLayout() {
    return (
        <div className="app-container">
            <header>
                <h2>FogForge</h2>
            </header>

            <main>
                <Outlet />
            </main>

            <footer>
                <p>© 2026 FogForge</p>
            </footer>
        </div>
    );
}

export default MainLayout;