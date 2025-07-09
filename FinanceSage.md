flowchart TD
    subgraph UsersBrowser [User's Browser (Client-Side)]
        A["User clicks 'Load Data' in DataImporter.tsx"] -- triggers --> B{FinancialDataContext}
        B -- "1. Dispatches 'IMPORT_START'" --> C[UI Components show loading state]
        B -- "2. Calls Genkit Flow w/ JSON data" --> E["/ai/flows/importAndCategorizeData"]
    end

    subgraph ServerSide [Server-Side (Genkit)]
        E -- "3. Receives request" --> F["importAndCategorizeData Flow"]
        F -- "4. Creates prompt & sends to Gemini" --> G["Gemini AI Model"]
        G -- "5. Returns structured, categorized data" --> F
        F -- "6. Sends data back in HTTP response" --> E
    end

    subgraph UsersBrowser2 [User's Browser (Client-Side)]
        E -- "7. Receives categorized data" --> B
        B -- "8. Dispatches 'IMPORT_SUCCESS' with data" --> H[UI Components hide loading state]
        B -- "9. Provides new data to consumers" --> I["Dashboard, TransactionsTable, etc."]
        I -- "10. React re-renders with new data" --> J["Charts and tables are populated"]
    end

    style A fill:#D9EAD3
    style J fill:#D9EAD3
    style G fill:#FCE5CD
