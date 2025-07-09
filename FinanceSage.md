flowchart TD
    %% Client Side
    subgraph UsersBrowser [User's Browser (Client-Side)]
        A@{shape: rounded}["User clicks 'Load Data' in DataImporter.tsx"]
        B@{shape: diam}{"FinancialDataContext"}
        C["UI Components show loading state"]
        H["UI Components hide loading state"]
        I["Dashboard, TransactionsTable, etc."]
        J@{shape: rounded}["Charts and tables are populated"]
        A -- triggers --> B
        B -- "1. Dispatches 'IMPORT_START'" --> C
        B -- "2. Calls Genkit Flow w/ JSON data" --> E
        E -- "7. Receives categorized data" --> B
        B -- "8. Dispatches 'IMPORT_SUCCESS' with data" --> H
        B -- "9. Provides new data to consumers" --> I
        I -- "10. React re-renders with new data" --> J
    end

    %% Server Side
    subgraph ServerSide [Server-Side (Genkit)]
        E@{shape: cyl}["/ai/flows/importAndCategorizeData"]
        F["importAndCategorizeData Flow"]
        G@{shape: stadium}["Gemini AI Model"]
        E -- "3. Receives request" --> F
        F -- "4. Creates prompt & sends to Gemini" --> G
        G -- "5. Returns structured, categorized data" --> F
        F -- "6. Sends data back in HTTP response" --> E
    end

    %% Styling
    style A fill:#D9EAD3,stroke:#388E3C,stroke-width:2px
    style J fill:#D9EAD3,stroke:#388E3C,stroke-width:2px
    style G fill:#FCE5CD,stroke:#F57C00,stroke-width:2px
    style E fill:#E3F2FD,stroke:#1976D2,stroke-width:2px
    style F fill:#E3F2FD,stroke:#1976D2,stroke-width:2px
    style B fill:#FFFDE7,stroke:#FBC02D,stroke-width:2px
    style H fill:#FFFDE7,stroke:#FBC02D,stroke-width:2px
