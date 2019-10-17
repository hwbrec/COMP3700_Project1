CREATE TABLE Customer (
	CustomerID	INTEGER NOT NULL,
	Name	TEXT NOT NULL,
	Phone	INTEGER,
	PaymentInfo	TEXT NOT NULL,
	PRIMARY KEY(CustomerID)
);
CREATE TABLE Product (
	ProductID	INTEGER NOT NULL,
	Name	TEXT NOT NULL,
	Price	DECIMAL NOT NULL,
	Quantity	DECIMAL,
	PRIMARY KEY(ProductID)
);
CREATE TABLE Purchase (
	PurchaseID	INTEGER NOT NULL,
	CustomerID	INTEGER NOT NULL,
	ProductID	INTEGER NOT NULL,
	Quantity	DECIMAL,
	PRIMARY KEY(PurchaseID),
	CONSTRAINT fk_ProductID FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
	CONSTRAINT fk_CustomerID FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
);

INSERT INTO Customer (CustomerID, Name, Phone, PaymentInfo)
VALUES(001, 'John Doe', 1234567654, 'Cash'), (002, 'Jane Doe', 9807651234, 'Cash'),
	(003, 'Mike Long', 5678978765, 'Card'), (004, 'Jake Su', 5681548963, 'Cash'),
	(005, 'Don Lon', 1256541235, 'Card');

INSERT INTO Product (ProductID, Name, Price, Quantity)
VALUES(001, 'Tent', 50.00, 5.0), (002, 'Grill', 75.00, 2.0),
	(003, 'Lighter', 1.50, 20.0), (004, 'Lantern', 20.00, 5.0),
	(005, 'Wood Bundle', 10.00, 10.0);

INSERT INTO Purchase (PurchaseID, CustomerID, ProductID, Quantity)
VALUES(001, 003, 002, 1.0), (002, 001, 001, 1.0), (003, 004, 001, 2.0),
	(004, 003, 003, 5.0), (005, 005, 002, 1.0), (006, 002, 003, 3.0),
	(007, 002, 004, 2.0), (008, 002, 005, 3.0), (009, 004, 004, 1.0),
	(010, 005, 005, 4.0);