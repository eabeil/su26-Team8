<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PetPal - Dashboard</title>
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
                        class="text-sm font-semibold text-black hover:text-black underline transition-colors">Dashboard</a>
                    <a href="/provider/${provider.id}/customer-list"
                        class="text-sm font-semibold text-black hover:text-black transition-colors">Customers</a>
                    <a href="/" class="text-sm font-semibold text-black hover:text-black transition-colors">Logout</a>
                </nav>
            </div>
    </header>

    <main class="max-w-4xl mx-auto px-6 py-10 space-y-6">

        <!-- PROFILE HEADER -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <div class="flex flex-col sm:flex-row gap-6">
                <img src="${provider.imageUrl}" alt="Profile picture"
                    class="w-24 h-24 rounded-full object-cover border border-gray-200 flex-shrink-0">
                <div class="flex-1">
                    <h2 class="text-xl font-extrabold tracking-tight mb-2">${provider.name}</h2>
                    <p class="text-sm text-gray-600 mb-6">${provider.address}</p>
                    <p class="text-sm text-gray-600 mb-6">${provider.email}</p>
                    <p class="text-sm text-gray-600 mb-6">${provider.phone}</p>
                    <p class="text-sm text-gray-500 leading-relaxed">${provider.description}
                    </p>
                    <a href="/provider/${provider.id}/profile-edit"
                        class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Edit
                    </a>
                </div>
            </div>
        </section>

        <!-- REVIEWS PREVIEW -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My ratings
            </h3>
            <p class="text-center text-2xl font-extrabold tracking-tight mb-2">75% like / 25% dislike (4 reviews)</p>
            <a href="/provider/${providerId}/reviews"
                class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">
                View and Respond to Reviews
            </a>
        </section>

        <!-- SERVICES LIST -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My Services
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
                <a href="/provider/${providerId}/jobs-edit"
                    class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Edit
                </a>
            </div>
        </section>

        <!-- UPDATES -->
        <section class="bg-white border border-gray-200 rounded-xl shadow-sm p-8">
            <h3 class="text-xs text-center text-gray-500 uppercase tracking-widest font-semibold mb-2">
                My Updates
            </h3>
            <div class="space-y-6">

                <#list updates as update>
                    <article class="border border-gray-200 rounded-lg p-8">
                        <img src="${update.imageUrl}" alt="Update picture" class="w-full h-auto object-cover">
                        <div class="mt-5">
                            <p class="text-sm text-gray-500">
                                ${update.description}
                            </p>
                            <p class="text-xs text-gray-500 mt-1">
                                ${update.formattedCreatedAt}
                            </p>
                        </div>
                        <div class="flex items-center gap-6 mt-3">
                            <a href="/provider/${provider.id}/update/${update.id}/delete"
                                class="ml-auto px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Delete
                            </a>
                        </div>
                    </article>
                </#list>

                <div class="mt-5 border border-gray-200 rounded-lg p-4">
                    <h4 class="text-sm font-semibold mb-2">
                        Make a New Post
                    </h4>
                    <form class="space-y-6" action="/provider/${provider.id}/update/create" method="post">
                        <div>
                            <input type="text" name="title" placeholder="Title" required
                                value="${(updateCreateDto.title)!}"
                                class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all resize-none">
                            <input type="text" name="imageUrl" placeholder="Image Link" required
                                value="${(updateCreateDto.imageUrl)!}"
                                class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all resize-none">
                            <textarea rows="4" placeholder="What's the update?" required name="description"
                                class="w-full px-6 py-4 text-sm border border-gray-200 rounded-lg focus:outline-none focus:ring-2 focus:ring-black focus:border-transparent transition-all"></textarea>
                            <button type="submit"
                                class="flex justify-center px-6 py-4 text-white bg-black rounded-lg hover:bg-gray-500 transition-colors font-medium shadow-sm">Post
                            </button>
                        </div>
                    </form>
                </div>
            </div>
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

</html>