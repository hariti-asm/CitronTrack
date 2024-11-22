## 🔗 API Endpoints

### 🏡 Farm Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/farms` | Create a new farm |
| PUT | `/farms/{id}` | Update an existing farm |
| DELETE | `/farms/{id}` | Delete a farm |
| GET | `/farms/{id}` | Get farm by ID |
| GET | `/farms` | Get all farms |
| GET | `/farms/search` | Search farms with filters |

#### 🔍 Farm Search Parameters
- `name`: Farm name
- `location`: Farm location
- `minArea`: Minimum farm area
- `maxArea`: Maximum farm area
- `createdAfter`: Created after date
- `createdBefore`: Created before date
- `minFields`: Minimum number of fields

### 🌾 Field Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/fields` | Create a new field |
| PUT | `/fields/{id}` | Update field information |
| DELETE | `/fields/{id}` | Delete a field |
| GET | `/fields/{id}` | Get field by ID |
| GET | `/fields` | Get all fields |
| GET | `/fields/farm/{farmId}` | Get all fields for a specific farm |
| GET | `/fields/search` | Search fields with filters |

#### 🔍 Field Search Parameters
- `name`: Field name
- `area`: Field area
- `farmId`: Associated farm ID
- `status`: Field status
- `createdAfter`: Created after date

### 🌳 Tree Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/trees` | Plant a new tree |
| PUT | `/trees/{id}` | Update tree information |
| DELETE | `/trees/{id}` | Remove a tree |
| GET | `/trees/{id}` | Get tree by ID |
| GET | `/trees` | Get all trees |
| GET | `/trees/field/{fieldId}` | Get all trees in a field |
| GET | `/trees/search` | Search trees with criteria |

#### 🔍 Tree Search Parameters
- `species`: Tree species
- `age`: Tree age
- `health`: Tree health status
- `fieldId`: Field location
- `plantedAfter`: Planting date range

### 🍋 Harvest Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/harvests` | Record new harvest |
| PUT | `/harvests/{id}` | Update harvest record |
| DELETE | `/harvests/{id}` | Delete harvest record |
| GET | `/harvests/{id}` | Get harvest by ID |
| GET | `/harvests` | Get all harvests |
| GET | `/harvests/field/{fieldId}` | Get harvests by field |
| GET | `/harvests/search` | Search harvests |

#### 🔍 Harvest Search Parameters
- `fieldId`: Field identifier
- `startDate`: Harvest start date
- `endDate`: Harvest end date
- `minQuantity`: Minimum quantity
- `status`: Harvest status

### 📝 Harvest Detail Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/harvest-details` | Add harvest details |
| PUT | `/harvest-details/{id}` | Update harvest details |
| DELETE | `/harvest-details/{id}` | Delete harvest detail |
| GET | `/harvest-details/{id}` | Get detail by ID |
| GET | `/harvest-details/harvest/{harvestId}` | Get details for harvest |
| GET | `/harvest-details/search` | Search harvest details |

#### 🔍 Harvest Detail Search Parameters
- `harvestId`: Associated harvest
- `quality`: Product quality grade
- `minQuantity`: Minimum quantity
- `maxQuantity`: Maximum quantity
- `date`: Processing date

### 💰 Sale Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/sales` | Record new sale |
| PUT | `/sales/{id}` | Update sale record |
| DELETE | `/sales/{id}` | Delete sale record |
| GET | `/sales/{id}` | Get sale by ID |
| GET | `/sales` | Get all sales |
| GET | `/sales/harvest/{harvestId}` | Get sales by harvest |
| GET | `/sales/search` | Search sales records |

#### 🔍 Sale Search Parameters
- `harvestId`: Associated harvest
- `minPrice`: Minimum sale price
- `maxPrice`: Maximum sale price
- `buyer`: Buyer information
- `dateRange`: Sale date range
- `status`: Sale status

### 📊 Analytics Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/analytics/harvest/summary` | Get harvest statistics |
| GET | `/analytics/sales/revenue` | Get revenue reports |
| GET | `/analytics/trees/health` | Get tree health overview |
| GET | `/analytics/fields/productivity` | Get field productivity metrics |

### 🔄 Common Response Status Codes

- `200 OK`: Successful operation
- `201 Created`: Resource successfully created
- `400 Bad Request`: Invalid input
- `404 Not Found`: Resource not found
- `500 Internal Server Error`: Server error

### 📝 Notes

- All endpoints support pagination where applicable using `page` and `size` parameters
- Dates should be provided in ISO 8601 format (YYYY-MM-DD)
- Authentication tokens should be included in the Authorization header
- Response format is consistently JSON
- Bulk operations are supported with `/bulk` suffix where applicable
