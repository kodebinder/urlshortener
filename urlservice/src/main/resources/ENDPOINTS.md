#POST:
http://localhost:9021/api/v1/urls
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
http://localhost:9021/api/v1/urls/fd069fac

#Exception:
http://localhost:9021/api/redis/v1/4d4f2dca779797979

---------------------------------------------------------------------

#POST:
http://localhost:9021/api/v2/urls
Request Body:
{
"urlId":1,
"urlName":"https://www.google.com",
"email":"pushkarchauhan91@gmail.com",
"userName":"pushkar6891",
"password":"Welcome@123",
"phoneNumber":"9826396465"
}

Response:
{
"id": 0,
"urlId": "50328aa4",
"urlName": "https://www.google.com",
"email": "pushkarchauhan91@gmail.com",
"userName": "pushkar6891",
"password": "Welcome@123",
"phoneNumber": "9826396465"
}
Status : 201 Created

#GET
http://localhost:9021/api/v2/urls/50328aa4

#Exception:
http://localhost:9021/api/mongo/v1/50328aa46868686

---------------------------------------------------------------------

#POST:
http://localhost:9021/api/v3/urls
Request Body:
{
"urlId":1,
"urlName":"https://www.google.com",
"email":"pushkarchauhan91@gmail.com",
"userName":"pushkar6891",
"password":"Welcome@123",
"phoneNumber":"9826396465"
}

Response:
{
"id": 0,
"urlId": "50328aa4",
"urlName": "https://www.google.com",
"email": "pushkarchauhan91@gmail.com",
"userName": "pushkar6891",
"password": "Welcome@123",
"phoneNumber": "9826396465"
}
Status : 201 Created

#GET
http://localhost:9021/api/v2/urls/50328aa4

#Exception:
http://localhost:9021/api/mongo/v1/50328aa46868686

---------------------------------------------------------------------
#POST:
http://localhost:8765/urls
Request Body:
{
"urlName":"https://www.google.com/?id=101",
"email":"pushkarchauhan91@gmail.com",
"userName":"pushkar123",
"password":"Welcome@123",
"phoneNumber":"9826396465"
}

Response:
{
"id": 1,
"urlId": "44d91984",
"urlName": "https://www.google.com/?id=101099",
"shortUrlName": "http://localhost:8765/urls/44d91984",
"userName": "pushkar123"
}
Status : 201 Created

#GET
http://localhost:8765/urls/44d91984

#GET
http://localhost:8765/users

#Exception:
http://localhost:8765/urls/44d91984123456789
{
"message": "Bad Request Exception is thrown : URL_ID_NOT_VALID",
"httpStatus": "BAD_REQUEST",
"localDateTime": {
"year": 2022,
"month": "APRIL",
"nano": 87000000,
"monthValue": 4,
"dayOfMonth": 20,
"hour": 16,
"minute": 52,
"second": 58,
"dayOfWeek": "WEDNESDAY",
"dayOfYear": 110,
"chronology": {
"id": "ISO",
"calendarType": "iso8601"
}
}
}

---------------------------------------------------------------------