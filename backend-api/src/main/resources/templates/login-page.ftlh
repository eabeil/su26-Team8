<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Login</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50 flex items-center justify-center min-h-screen font-sans text-black flex flex-col">

    <!--login container-->
    <main class="w-full max-w-md p-8 bg-white border border-gray-200 rounded-xl shadow-sm">
        <div class="text-center mb-8">
            <h1 class="text-3xl font-extrabold tracking-tight">PetPal</h1>
            <p class="text-sm text-gray-500 mt-2">Find trusted pet care in your neighborhood.</p>
        </div>
    

        <#if successMessage??>
            <p class="mb-5 px-4 py-3 rounded-lg bg-green-50 border border-green-200 text-green-700 text-sm font-medium" role="status">
                ${successMessage}
            </p>
        </#if>
        <#if loginError??>
            <p class="mb-5 px-4 py-3 rounded-lg bg-red-50 border border-red-200 text-red-700 text-sm font-medium" role="alert">
                ${loginError}
            </p>
        </#if>


        <form method="post" action="/customer/login" class="space-y-5">
                <!--email input-->
            <div>
                <label class="block text-sm font-semibold mb-1">Email</label>
                <input type="text" name="email" value="${email!}" placeholder="name@example.com" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
            </div> 
                <!--password input-->
            <div>
                <label class="block text-sm font-semibold mb-1">Password</label>
                <input type="password" name="password" placeholder="••••••••" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
            </div>
            <!--dashboard slection-->
            <div class="pt-4 space-y-3">
                <p class="text-xs text-center text-gray-400 uppercase tracking-widest font-semibold mb-3">Select your dashboard</p>    
                <!-- customer login option-->
                <button  method="get" action="/provider/login" type="submit" class="flex justify-center w-full px-4 py-2.5 text-white bg-black rounded-lg">
                    Log in as Pet Owner
                </button>    
                <button  formmethod="post" formaction="/provider/login" type="submit" class="flex justify-center w-full px-4 py-2.5 text-black bg-white border-2 border-black rounded-lg">
                    Log in as Provider
                </button>
            </div>
        </form>

        <!--sign up card-->
        <div class="pt-4 space-y-3">
            <p class="text-xs text-center text-gray-400 uppercase tracking-widest font-semibold mb-3">Don't have an account?</p>
        
            <a href="/customer/sign-up" class="flex justify-center w-full px-4 py-2.5 text-white bg-black rounded-lg hover:bg-gray-800 transition-colors font-medium shadow-sm">
                Sign up as Customer
            </a>
            <a href="/provider/sign-up/" class="flex justify-center w-full px-4 py-2.5 text-black bg-white border-2 border-black rounded-lg hover:bg-gray-50 transition-colors font-medium">
                Sign up as Provider
            </a>
        </div>    
    </main>

</body>
</html>    
