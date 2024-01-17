isLocal = true

ip = '3.88.46.191'

if isLocal
	ip = 'localhost'
api http://`ip`:8080/scraping/products

total = api_json.length
//echo `total`

result = api_json

for n from 1 to total
	echo `result[n-1].producto` `result[n-1].canal` `result[n-1].precio` 
	
	id 		 	 = result[n-1].id
	producto 	 = result[n-1].producto
	marca 	 	 = result[n-1].marca
	presentacion = result[n-1].presentacion
	tamano 	 	 = result[n-1].tamano
	url 	 	 = result[n-1].url
	canal  		 = result[n-1].canal
	
	//echo url `url`	
	//if n < 5
		//continue
		
	https://`url.replace('https://','')`	
	
	if canal equals to 'Carulla'
		//echo Carulla
		wait 18
		//xpath = '/html/body/div[2]/div/div[1]/div/div/div/div[3]/div/div[6]/div/section/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div[2]/div/span'
		xpath = '//div[@class="exito-product-details-3-x-classForMainOffer"]/div[1]/div[1]/div/div/div[2]/div/span'
		//xpath2 = '/html/body/div[2]/div/div[1]/div/div/div/div[3]/div/div[6]/div/section/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div[3]/div/span'
		xpath2 = '//div[@class="exito-product-details-3-x-classForMainOffer"]/div[1]/div[1]/div/div/div[2]/div[1]/span'
		if isLocal
			xpath2 = '/html/body/div[2]/div/div[1]/div/div/div/div[3]/div/div[6]/div/section/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div[2]/div[1]/span'
		if exist(xpath2)
			read `xpath2` to price
		else
			if exist(xpath)
				read `xpath` to price
				echo Precio Carulla `price`
			else
				echo ------ ERROR Problemas con la pagina -> `url` ----
				continue
	else if canal equals to 'Jumbo'
		wait 10
		//echo Jumbo
		//xpath = '/html/body/div[2]/div/div[1]/div/div/div/div[1]/div/div[1]/div/section/div/div/div/div[2]/div/div/div[2]/div/div[4]/div/div/div[2]/div/div/div/div/div/div/div/div[1]/div/div/div/div'
		xpath = '//*[@id="items-price"]/div/div'
		read `xpath` to price
		echo Precio Jumbo `price`
	else if canal equals to 'Exito'
		//echo Exito
		wait 10
		
		//xpath =  '/html/body/div[1]/main/section/section[1]/section[2]/section[1]/section/section[2]/div/div[2]/p'			
		//xpath2 = '/html/body/div[1]/main/section/section[1]/section[2]/section[1]/section/section[2]/div/div[3]/p[1]'
		
		//xpath =  '/html/body/div[1]/main/section/section[1]/section[2]/section[1]/section/section[2]/div/div[2]/p'				
		xpath =  '/html/body/div[2]/div/div[1]/div/div/div/div[3]/div/div[11]/div/section/div/div[2]/div/div/div/div/div/div/div/div/div[1]/div[1]/div/div/div[2]/div/span'
		xpath2 = '/html/body/div[1]/main/section/section[1]/section[2]/section[1]/section/section[2]/div/div[3]/div/span/span[2]'		
		
		if exist(xpath2)
			read `xpath2` to price
		else
			read `xpath` to price
		echo Precio Exito `price` 	
	else if canal equals to 'Dislicores'
		wait 10
		echo Dislicores
		
		//keyboard [esc]
		//type //*[@id="react-select-2-input"] as [clear]C
		xpathInput = '//*[@id="react-select-2-input"]'
		if exist(xpathInput)
			click Soy mayor de 18
			wait 1
			//click //*[@id="react-select-2-input"]
			//type //*[@id="react-select-2-input"] as [clear]Medellín
			click //*[@class="css-8mmkcg"]
			click  Medellín
			//keyboard Medellín[enter]
			// /html/body/div[13]/div/div/div/section/div/div/div/div/div[3]/div/div/div[1]/div[1]
			wait 1
			click Continuar
			wait 5
		xpath = '/html/body/div[2]/div/div[1]/div/div/div/div[6]/div/div[1]/div/div/div[2]/section/div/div[2]/div/div[5]/div/div/span/span'
		read `xpath` to price
		echo Precio Dislicores `price`		
	else
		echo yet other instructions
		
	js begin
		price = price.replace(".","").replace("$ ","").replace("$","");
	js finish
	
	query = {"producto": producto, "marca": marca, "presentacion": presentacion, "tamano": tamano, "url": url, "canal": canal, "precio": price}
	//echo `query`
	// Configure HTTP headers
	api_config = {method:'PUT', header:['content-type: application/json; charset=iso-8859-1'], body:query};

	api http://`ip`:8080/scraping/products/`id`
	echo ------ `result[n-1].precio` ---- Update ----- `price` ---- 