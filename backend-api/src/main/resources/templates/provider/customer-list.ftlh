<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Directory - PetPal</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 font-sans text-black min-h-screen">

    <!-- SITE HEADER -->
    <header>
        <div class="bg-white border-b border-gray-200">
            <div class="max-w-4xl mx-auto px-6 py-4 flex items-center">
                <h1 class="text-2xl font-extrabold tracking-tight">PetPal</h1>
                <nav class="ml-auto flex items-center gap-6">
                    <a href="/provider/${provider.id}/dashboard"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Dashboard</a>
                    <a href="/provider/${provider.id}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black underline transition-colors">Customers</a>
                    <a href="/" class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
        </div>
    </header>

    <div class="max-w-6xl mx-auto px-8 md:px-12 py-10 flex-grow w-full flex flex-col md:flex-row gap-8">

        <!--info bar-->
        <aside class="w-full md:w-1/3 lg:w-1/4">
            <!---->
            <div class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm sticky top-24">
                <h2 class="text-lg font-bold mb-5">Filter Search</h2>

                <form action="/provider/${provider.id}/customer-list" method="get" class="space-y-5">
                    <div>
                        <label
                            class="block text-xs font-semibold uppercase tracking-wider text-gray-500 mb-2">Name</label>
                        <div class="relative">
                            <input type="text" name="nameQuery" value="${nameQuery}"
                                placeholder="Search by name..."
                                class="w-full pl-10 pr-4 py-2.5 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-black text-sm transition-shadow">
                            <svg class="w-4 h-4 text-gray-400 absolute left-3 top-1/2 transform -translate-y-1/2"
                                fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                xmlns="http://www.w3.org/2000/svg">
                                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                    d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                            </svg>
                        </div>
                    </div>

                    <!--button-->
                    <div class="pt-4 border-t border-gray-100">
                        <button type="submit"
                            class="w-full bg-black text-white px-4py-2.5 rounded-lg font-semibold hover:bg-gray-800 transition-colors text-sm">
                            Search
                        </button>
                    </div>
                </form>
            </div>
        </aside>

        <!--right side search-->
        <main class="w-full md:w-2/3 lg:w-3/4">

            <!--cards-->
            <div class="space-y-6">

                <#list customers as customer>
                    <div
                        class="bg-white border border-gray-200 rounded-2xl p-6 shadow-sm flex flex-col sm:flex-row gap-6 hover:shadow-md transition-shadow">
                        <img src="${customer.imageUrl}" alt="${customer.name}"
                            class="w-24 h-24 sm:w-32 sm:h-32 rounded-xl object-cover border border-gray-200 flex-shrink-0">
                        <div class="flex-grow flex flex-col">
                            <div class="flex justify-between items-start">
                                <div>
                                    <h3 class="text-xl font-bold">${customer.name}</h3>
                                    <p class="text-sm text-gray-600 mb-6">Pet Owner</p>
                                </div>
                            </div>
                        </div>
                        <div class="mt-auto flex justify-end">
                            <a href="/provider/${provider.id}/customer-profile/${customer.id}" class="text-sm
                                font-semibold border-2 border-black px-6 -y-2 rounded-lg hover:bg-black hover:text-white
                                transition-colors">
                                Profile
                            </a>
                        </div>
                    </div>
                </#list>

            </div>

    </div>
    </main>

    </div>