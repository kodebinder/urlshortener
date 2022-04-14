#POST:
http://localhost:9031/api/v1/urls
Request Body:
{
"urlId":1,
"urlName":"https://www.google.com/search?q=stackoverflow&sxsrf=APq-WBvEi2H3Kd7tRq27AleiAea-PgyKIA%3A1649819076120&ei=xD1WYrTzBqy94-EPntCQoAE&oq=stackove&gs_lcp=Cgdnd3Mtd2l6EAMYADIICAAQgAQQsQMyCwgAEIAEELEDEIMBMgcIABCABBAKMgUIABCABDIFCAAQgAQyCggAEIAEELEDEAoyBwgAEIAEEAoyBQgAEIAEMgcIABCABBAKMgUIABCABDoHCCMQsAMQJzoHCAAQRxCwAzoECCMQJzoECAAQQzoKCC4QxwEQ0QMQQzoFCAAQkQI6BAguEEM6BwgAELEDEEM6DQguELEDEIMBENQCEEM6EQguEIAEELEDEIMBEMcBEKMCOgsILhCABBDHARCjAkoECEEYAEoECEYYAFCtDFjTH2DhJGgDcAF4AIABbogB2gWSAQM2LjKYAQCgAQHIAQrAAQE&sclient=gws-wiz",
"email":"pushkarchauhan91@gmail.com",
"userName":"pushkar6891",
"password":"Welcome@123",
"phoneNumber":"9826396465"
}

Response:
{
"id": 1,
"urlId": "fd069fac",
"urlName": "https://www.google.com/search?q=stackoverflow&sxsrf=APq-WBvEi2H3Kd7tRq27AleiAea-PgyKIA%3A1649819076120&ei=xD1WYrTzBqy94-EPntCQoAE&oq=stackove&gs_lcp=Cgdnd3Mtd2l6EAMYADIICAAQgAQQsQMyCwgAEIAEELEDEIMBMgcIABCABBAKMgUIABCABDIFCAAQgAQyCggAEIAEELEDEAoyBwgAEIAEEAoyBQgAEIAEMgcIABCABBAKMgUIABCABDoHCCMQsAMQJzoHCAAQRxCwAzoECCMQJzoECAAQQzoKCC4QxwEQ0QMQQzoFCAAQkQI6BAguEEM6BwgAELEDEEM6DQguELEDEIMBENQCEEM6EQguEIAEELEDEIMBEMcBEKMCOgsILhCABBDHARCjAkoECEEYAEoECEYYAFCtDFjTH2DhJGgDcAF4AIABbogB2gWSAQM2LjKYAQCgAQHIAQrAAQE&sclient=gws-wiz",
"email": "pushkarchauhan91@gmail.com",
"userName": "pushkar6891",
"password": "Welcome@123",
"phoneNumber": "9826396465"
}
Status : 201 Created

#GET
http://localhost:9031/api/v1/urls/fd069fac

#Exception:
http://localhost:9191/api/redis/v1/4d4f2dca779797979

---------------------------------------------------------------------

#POST:
http://localhost:9031/api/v2/urls
Request Body:
{
"urlId":1,
"urlName":"https://www.google.com/search?q=stackoverflow&sxsrf=APq-WBvEi2H3Kd7tRq27AleiAea-PgyKIA%3A1649819076120&ei=xD1WYrTzBqy94-EPntCQoAE&oq=stackove&gs_lcp=Cgdnd3Mtd2l6EAMYADIICAAQgAQQsQMyCwgAEIAEELEDEIMBMgcIABCABBAKMgUIABCABDIFCAAQgAQyCggAEIAEELEDEAoyBwgAEIAEEAoyBQgAEIAEMgcIABCABBAKMgUIABCABDoHCCMQsAMQJzoHCAAQRxCwAzoECCMQJzoECAAQQzoKCC4QxwEQ0QMQQzoFCAAQkQI6BAguEEM6BwgAELEDEEM6DQguELEDEIMBENQCEEM6EQguEIAEELEDEIMBEMcBEKMCOgsILhCABBDHARCjAkoECEEYAEoECEYYAFCtDFjTH2DhJGgDcAF4AIABbogB2gWSAQM2LjKYAQCgAQHIAQrAAQE&sclient=gws-wiz",
"email":"pushkarchauhan91@gmail.com",
"userName":"pushkar6891",
"password":"Welcome@123",
"phoneNumber":"9826396465"
}

Response:
{
"id": 0,
"urlId": "1",
"urlName": "https://www.google.com/search?q=stackoverflow&sxsrf=APq-WBvEi2H3Kd7tRq27AleiAea-PgyKIA%3A1649819076120&ei=xD1WYrTzBqy94-EPntCQoAE&oq=stackove&gs_lcp=Cgdnd3Mtd2l6EAMYADIICAAQgAQQsQMyCwgAEIAEELEDEIMBMgcIABCABBAKMgUIABCABDIFCAAQgAQyCggAEIAEELEDEAoyBwgAEIAEEAoyBQgAEIAEMgcIABCABBAKMgUIABCABDoHCCMQsAMQJzoHCAAQRxCwAzoECCMQJzoECAAQQzoKCC4QxwEQ0QMQQzoFCAAQkQI6BAguEEM6BwgAELEDEEM6DQguELEDEIMBENQCEEM6EQguEIAEELEDEIMBEMcBEKMCOgsILhCABBDHARCjAkoECEEYAEoECEYYAFCtDFjTH2DhJGgDcAF4AIABbogB2gWSAQM2LjKYAQCgAQHIAQrAAQE&sclient=gws-wiz",
"email": "pushkarchauhan91@gmail.com",
"userName": "pushkar6891",
"password": "Welcome@123",
"phoneNumber": "9826396465"
}
Status : 201 Created

#GET
http://localhost:9191/api/mongo/v1/50328aa4

Response Body:
{
"id": 0,
"urlId": "50328aa4",
"urlName": "https://www.google.com"
}
Status : 200 OK

#Exception:
http://localhost:9191/api/mongo/v1/50328aa46868686