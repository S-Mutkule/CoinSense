# Application Architecture and Data Flow

This document outlines the architecture of the FinanceSage application and illustrates the data flow when a user imports financial data.

## Key Components

- **Frontend (Next.js/React)**: Manages the user interface and client-side state.
- **State Management (React Context)**: A client-side "in-memory" store for financial data.
- **AI Backend (Genkit)**: Server-side logic that communicates with the Gemini AI model.

## Data Import Flow Diagram

The following diagram shows the sequence of events when a user imports their financial data.

```mermaid
graph TD
    subgraph "User's Browser (Client-Side)"
        A["User clicks 'Load Data' in DataImporter.tsx"] --triggers--> B{FinancialDataContext};
        B --1. Dispatches 'IMPORT_START' --> C[UI Components show loading state];
        B --2. Calls Genkit Flow w/ JSON data--> E[/ai/flows/importAndCategorizeData];
    end

    subgraph "Server-Side (Genkit)"
        E --3. Receives request--> F["importAndCategorizeData Flow"];
        F --4. Creates prompt & sends to Gemini--> G(Gemini AI Model);
        G --5. Returns structured, categorized data--> F;
        F --6. Sends data back in HTTP response--> E;
    end

    subgraph "User's Browser (Client-Side)"
        E --7. Receives categorized data--> B;
        B --8. Dispatches 'IMPORT_SUCCESS' with data--> H[UI Components hide loading state];
        B --9. Provides new data to consumers--> I{Dashboard, TransactionsTable, etc.};
        I --10. React re-renders with new data--> J["Charts and tables are populated"];
    end

    style A fill:#D9EAD3
    style J fill:#D9EAD3
    style G fill:#FCE5CD
```


## Persistence & Future improvements

Currently, all data is stored in-memory on the client and is lost upon page refresh.

For a production application, the next logical steps would be:
1.  **Add User Authentication** (e.g., Firebase Auth).
2.  **Integrate a Database** (e.g., Firestore) to persist data between sessions, linking it to the authenticated user.
