# setur-contact-service

# CreateContact
POST http://localhost:8080/contacts
Content-Type: application/json

{
"name": "Ahmet",
"surname": "Say",
"company": "Seturrrr",
"communications": [
{
"type": "PHONE",
"value": "3333333"},
{
"type": "EMAIL",
"value": "mail@gmail.com"
},
{
"type": "LOCATION",
"value": "41.08678235159243, 28.831090327637097"
}
]
}

# UpdateContact
PUT http://localhost:8080/contacts/98ff1515-0630-4e7f-a588-447743518ac5
Content-Type: application/json

{
"name": "Bilal",
"surname": "Say",
"company": "Setur2",
"communications": [
{
"id": "1c00ec88-a940-417d-ba72-6e5bd3b49b16",
"type": "PHONE",
"value": "3333333"
},
{
"id": "18c51643-107a-46c8-8d47-0c1b021efecc",
"type": "EMAIL",
"value": "mail@gmail.com"
},
{
"id": "105e0fc8-f718-45ea-8cea-22e6b42fc47a",
"type": "LOCATION",
"value": "41.08678235159242, 28.831090327637096"
},
{
"type": "EMAIL",
"value": "mail@hotmail.com"
}
]
}

# DeleteContact
DELETE http://localhost:8080/contacts/98ff1515-0630-4e7f-a588-447743518ac5

# AddCommunicationToContact
POST http://localhost:8080/communications
Content-Type: application/json

{
"contactId": "865b84ec-c3c5-4c39-8783-ab8b9fa2a5af",
"type": "PHONE",
"value": "2432543546"
}

# UpdateCommunication
PUT http://localhost:8080/communications/21b3fc78-40f1-4077-8e7e-38480f1e95db
Content-Type: application/json

{
"type": "EMAIL",
"value": "mail@seturrrr.com"
}

# DeleteCommunication
DELETE http://localhost:8080/communications/21b3fc78-40f1-4077-8e7e-38480f1e95db

# ListContacts
GET http://localhost:8080/contacts

# GetContactDetail
GET http://localhost:8080/contacts/9fe21d8b-0f31-420c-a65b-d32feb801fa2

# GenerateReport
POST http://localhost:8080/reports

# ListReports
GET http://localhost:8080/reports

# GetReportDetail
GET http://localhost:8080/reports/ffe8326e-c1a9-42ec-920e-99dd37ce996b

# Kafka Topic
contact.ReportGenerate