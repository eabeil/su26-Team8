<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Reviews</title>
    <!--tailwind setup-->
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-50 min-h-screen font-sans text-black">

    <!-- SITE HEADER -->
    <header>
        <div class="bg-white border-b border-gray-200">
            <div class="max-w-4xl mx-auto px-6 py-4 flex items-center">
                <h1 class="text-2xl font-extrabold tracking-tight">PetPal</h1>
                <nav class="ml-auto flex items-center gap-6">
                    <a href="/provider/${provider.id}/dashboard"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Dashboard</a>
                    <a href="/provider/${provider.id}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/" class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- EDIT SERVICES -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-6">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-6">
                Edit Services
            </h3>

            <div class="overflow-x-auto">
                <table class="w-full text-sm text-left border-collapse">
                    <thead>
                        <tr class="bg-gray-50 text-xs text-gray-500 uppercase tracking-wider">
                            <th class="px-6 py-4 rounded-lg">Data / Time</th>
                            <th class="px-6 py-4">Service</th>
                            <th class="px-6 py-4">Duration</th>
                            <th class="px-6 py-4 rounded-lg">Price</th>
                        </tr>
                    </thead>
                    <#list jobs as job>
                        <tbody>
                            <tr class="border-b border-gray-200">
                                <td class="px-6 py-4 text-gray-500">${job.formattedTime}</td>
                                <td class="px-6 py-4 text-gray-500">${job.name}</td>
                                <td class="px-6 py-4 text-gray-500">${job.duration}</td>
                                <td class="px-6 py-4 text-gray-500">$ ${job.price}</td>
                            </tr>
                        </tbody>
                    </#list>
                </table>
            </div>
        </section>

        <!-- ADD NEW SERVICE -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-6">
                Add a New Service
            </h3>
            <form method="post" action="/provider/${provider.id}/job/create" class="space-y-6">
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                            Time
                        </label>
                        <input type="datetime-local" name="time" required
                            class="items-center px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                    </div>
                    <div>
                        <label class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                            Service
                        </label>
                        <input type="text" name="name" placeholder="e.g. Dog Walking" required
                            class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                    </div>
                </div>
                <div class="grid grid-cols-1 sm:grid-cols-2 gap-6">
                    <div>
                        <label class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                            Duration
                        </label>
                        <input type="text" name="duration" placeholder="e.g. 30 min" required
                            class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                    </div>
                    <div>
                        <label class="block text-xs text-gray-500 uppercase tracking-widest font-semibold mb-2">
                            Price (dollars)
                        </label>
                        <input type="number" name="price" step="0.01" min="0" placeholder="20" required
                            class="w-full px-6 py-4 text-sm border order-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all">
                    </div>
                </div>
                <div class="flex gap-4">
                    <button type="submit"
                        class="flex justify-center flex-1 px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                        Add Service
                    </button>
                    <a href="/provider/${provider.id}/dashboard"
                        class="flex justify-center flex-1 px-6 py-4 text-black bg-white border-2 border-black rounded-lg hover:bg-gray-500 transition-colors font-medium">
                        Done
                    </a>
                </div>
            </form>
        </section>

    </main>

    <!-- save page position on reload -->
    <script>
        document.querySelectorAll('form').forEach(form => {
            form.addEventListener('submit', () => {
                sessionStorage.setItem('scrollY', window.scrollY);
            });
        });

        window.addEventListener('DOMContentLoaded', () => {
            const y = sessionStorage.getItem('scrollY');
            if (y !== null && window.location.hash === '#scrolly') {
                window.scrollTo(0, parseInt(y, 10));
                sessionStorage.removeItem('scrollY');
            }
        })
    </script>
</body>