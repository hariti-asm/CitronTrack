# CitronTrack: Smart Citrus Farm Management System 🍊

## About CitronTrack

CitronTrack is a comprehensive agricultural management system specifically designed for citrus farms, revolutionizing the way farm owners and managers handle their operations. By seamlessly integrating every aspect of citrus farming - from tree management to harvest tracking and sales analytics - CitronTrack brings modern technology to traditional farming practices.

## Why CitronTrack? 🌟

In today's rapidly evolving agricultural landscape, efficient farm management is crucial for success. CitronTrack addresses the unique challenges faced by citrus farmers:

- **Field Mapping & Organization** 🗺️
  Track and manage multiple fields across various locations, monitor soil conditions, and optimize land usage.

- **Tree Lifecycle Management** 🌳
  Monitor the health, growth, and productivity of individual trees, schedule maintenance, and track disease prevention.

- **Harvest Planning & Execution** 🍋
  Plan harvest schedules, record yields, and manage quality control processes with precision.

- **Sales & Revenue Tracking** 💰
  Streamline the sales process, track market prices, and analyze revenue patterns for better decision-making.

## Key Benefits

- **Increased Productivity** ⚡
  Automate routine tasks and streamline workflows to save time and reduce manual effort.

- **Data-Driven Decisions** 📊
  Access real-time analytics and insights to make informed decisions about farm operations.

- **Enhanced Traceability** 🔍
  Maintain detailed records of all farming activities, from planting to harvest and sales.

- **Cost Optimization** 💹
  Track expenses, identify inefficiencies, and optimize resource allocation.

- **Quality Control** ✔️
  Implement and monitor quality standards throughout the production process.

## Target Users 👥

- Citrus Farm Owners
- Farm Managers
- Agricultural Consultants
- Harvest Coordinators
- Sales Teams
- Quality Control Specialists

## Technology Stack 🛠️

Built using cutting-edge technologies and following best practices in software development, CitronTrack ensures reliability, scalability, and ease of use:

- **Backend**: Spring Boot with Java
- **Database**: PostgreSQL for robust data storage
- **API Documentation**: OpenAPI (Swagger) for clear API documentation
- **Security**: JWT-based authentication and role-based access control
- **Monitoring**: Built-in analytics and reporting tools

This modern farm management solution represents the future of citrus farming, where technology and agriculture meet to create more sustainable and profitable farming operations.
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
